package com.soict.hoangviet.cleanarchitecture.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.soict.hoangviet.cleanarchitecture.customview.ViewController;
import com.soict.hoangviet.cleanarchitecture.di.annotation.LayoutId;
import com.soict.hoangviet.data.apiservice.ApiError;
import com.soict.hoangviet.data.apiservice.ApiException;
import com.soict.hoangviet.data.apiservice.NetworkConnectionInterceptor;
import com.soict.hoangviet.data.models.base.BaseError;
import com.soict.hoangviet.data.models.base.ListResponse;
import com.soict.hoangviet.data.models.base.ObjectResponse;
import com.soict.hoangviet.data.models.base.Result;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.HttpException;

public abstract class BaseActivity<T extends ViewDataBinding> extends DaggerAppCompatActivity {

    protected T binding;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    protected ViewController mViewController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getClass().getAnnotation(LayoutId.class).value());
        initProgressDialog();
        mViewController = new ViewController(getSupportFragmentManager(), getFragmentContainerId());
        initViewModel();
        initView();
        initData();
        initListener();
    }

    private void initProgressDialog() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public abstract int getFragmentContainerId();

    @Override
    public void onBackPressed() {
        if (mViewController != null && mViewController.getCurrentFragment() != null) {
            if (mViewController.getCurrentFragment().backPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    public ViewController getViewController() {
        if (mViewController == null) {
            mViewController = new ViewController(getSupportFragmentManager(), getFragmentContainerId());
        }
        return mViewController;
    }

    @Nullable
    public void handleNetworkError(Throwable throwable) {
        ApiError apiError;
        Boolean isShowToast = true;
        if (throwable instanceof NetworkConnectionInterceptor.NoConnectivityException) {
            apiError = new ApiError(throwable.getMessage());
        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try {
                apiError = gsonFromJson(
                        httpException.response().errorBody().toString(),
                        ApiError.class
                );
            } catch (JsonParseException jfe) {
                apiError = new ApiError(Result.Message.ERROR_TRY_AGAIN);
            } catch (IOException ioe) {
                apiError = new ApiError(Result.Message.ERROR_TRY_AGAIN);
            } catch (IllegalStateException ile) {
                apiError = new ApiError(Result.Message.ERROR_TRY_AGAIN);
            } catch (Exception e) {
                apiError = new ApiError(Result.Message.ERROR_TRY_AGAIN);
            }
        } else if (throwable instanceof ConnectException
                || throwable instanceof SocketTimeoutException
                || throwable instanceof UnknownHostException
                || throwable instanceof IOException) {
            apiError = new ApiError(Result.Message.TIME_OUT);
        } else if (throwable instanceof BaseError) {
            apiError = new ApiError(throwable.getMessage(), ((BaseError) throwable).getCode());
            isShowToast = ((BaseError) throwable).isShowToast();
        } else {
            apiError = new ApiError(Result.Message.ERROR_TRY_AGAIN);
        }
        if (isShowToast) {
            if (apiError != null) {
                //show toast
            }
        } else {
            if (apiError != null) {
                ApiException apiException = apiError.getApiException();
                getError(apiException.getMessage(), apiException.getStatusCode());
            }
        }
    }

    private <T> T gsonFromJson(String json, Class<T> classOfT) throws Exception {
        try {
            return new Gson().fromJson(json, classOfT);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    protected void handleListResponse(ListResponse<?> response, boolean isShowToast) {
        switch (response.getType()) {
            case Result.LOADING:
                showLoading();
                break;
            case Result.SUCCESS:
                getListResponse(response.getData(), response.isRefresh(), response.isLoadingMore());
                hideLoading();
                break;
            case Result.ERROR:
                handleNetworkError(response.getError());
                hideLoading();
        }
    }

    protected <U> void handleObjectResponse(ObjectResponse<U> response) {
        switch (response.getType()) {
            case Result.LOADING:
                showLoading();
                break;
            case Result.SUCCESS:
                hideLoading();
                getObjectResponse(response.getData());
                break;
            case Result.ERROR:
                hideLoading();
                handleNetworkError(response.getError());
        }
    }

    protected void showLoading() {

    }

    protected void hideLoading() {

    }

    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadMore) {

    }

    protected <U> void getObjectResponse(U data) {

    }

    protected void getError(String error, int code) {

    }


    protected abstract void initViewModel();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
}
