package com.soict.hoangviet.cleanarchitecture.ui.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.soict.hoangviet.cleanarchitecture.customview.ViewController;
import com.soict.hoangviet.cleanarchitecture.di.annotation.LayoutId;
import com.soict.hoangviet.cleanarchitecture.ui.main.MainViewModel;
import com.soict.hoangviet.data.apiservice.ApiError;
import com.soict.hoangviet.data.apiservice.ApiException;
import com.soict.hoangviet.data.apiservice.NetworkConnectionInterceptor;
import com.soict.hoangviet.domain.models.base.BaseError;
import com.soict.hoangviet.domain.models.base.ListLoadMoreResponse;
import com.soict.hoangviet.domain.models.base.ListResponse;
import com.soict.hoangviet.domain.models.base.ObjectResponse;
import com.soict.hoangviet.domain.models.base.Result;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import retrofit2.HttpException;

public abstract class BaseFragment<T extends ViewDataBinding> extends DaggerFragment {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    protected MainViewModel mainViewModel;

    protected T binding;

    /**
     * The ViewController for control fragments in an activity
     */
    @Nullable
    protected ViewController mViewController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getClass().getAnnotation(LayoutId.class).value(), container, false);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
        initViewModel();
        initView();
        initData();
        initListener();
    }

    public void setViewController(ViewController viewController) {
        this.mViewController = viewController;
    }

    public void setData(HashMap<String, Object> data) {
        if (data == null || data.isEmpty()) {
            setArguments(new Bundle());
            return;
        }
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String) {
                bundle.putString(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof Double) {
                bundle.putDouble(entry.getKey(), ((Double) entry.getValue()));
            } else if (entry.getValue() instanceof Integer) {
                bundle.putInt(entry.getKey(), (Integer) entry.getValue());
            } else if (entry.getValue() instanceof Float) {
                bundle.putFloat(entry.getKey(), ((Float) entry.getValue()));
            } else if (entry.getValue() instanceof Boolean) {
                bundle.putBoolean(entry.getKey(), ((Boolean) entry.getValue()));
            } else if (entry.getValue() instanceof Parcelable) {
                bundle.putParcelable(entry.getKey(), ((Parcelable) entry.getValue()));
            } else if (entry.getValue() instanceof String[]) {
                bundle.putStringArray(entry.getKey(), (String[]) entry.getValue());
            } else if (entry.getValue() instanceof ArrayList) {
                if (((ArrayList) entry.getValue()).size() > 0 && ((ArrayList) entry.getValue()).get(0) instanceof String) {
                    bundle.putStringArrayList(entry.getKey(), (ArrayList<String>) entry.getValue());
                } else if (((ArrayList) entry.getValue()).size() > 0 && ((ArrayList) entry.getValue()).get(0) instanceof Parcelable) {
                    bundle.putParcelableArrayList(entry.getKey(), (ArrayList<? extends Parcelable>) entry.getValue());
                }
            }
        }
        setArguments(bundle);
    }

    public ViewController getViewController() {
        if (mViewController == null) {
            return ((BaseActivity) getActivity()).getViewController();
        } else {
            return mViewController;
        }
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

    protected void handleListResponse(ListResponse<?> response) {
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

    protected <U> void handleListLoadMoreResponse(ListLoadMoreResponse<U> response) {
        switch (response.getType()) {
            case Result.LOADING:
                showLoading();
                break;
            case Result.SUCCESS:
                hideLoading();
                getListResponse(response.getData().getData(), response.isRefresh(), response.isLoadingMore());
                break;
            case Result.ERROR:
                hideLoading();
                handleNetworkError(response.getError());
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
        mainViewModel.getLoadingLiveData().setValue(true);
    }

    protected void hideLoading() {
        mainViewModel.getLoadingLiveData().setValue(false);
    }

    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadMore) {

    }

    protected <U> void getObjectResponse(U data) {

    }

    protected void getError(String error, int code) {

    }

    public abstract void backFromAddFragment();

    public abstract boolean backPressed();

    public abstract void initView();

    protected abstract void initViewModel();

    public abstract void initData();

    public abstract void initListener();
}
