package com.soict.hoangviet.data.di

import android.content.Context
import com.soict.hoangviet.data.BuildConfig
import com.soict.hoangviet.data.apiservice.ApiService
import com.soict.hoangviet.data.apiservice.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        var mOkHttpClientBuilder = OkHttpClient.Builder()
        mOkHttpClientBuilder.connectTimeout(20L, TimeUnit.SECONDS)
        mOkHttpClientBuilder.readTimeout(20L, TimeUnit.SECONDS)
        mOkHttpClientBuilder.writeTimeout(20L, TimeUnit.SECONDS)
        mOkHttpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
            .addInterceptor(NetworkConnectionInterceptor(context))
        if (BuildConfig.DEBUG) {
            mOkHttpClientBuilder.let {
                if (!it.interceptors().contains(logging)) {
                    it.addInterceptor(logging)
                }
            }
        }
        return mOkHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}