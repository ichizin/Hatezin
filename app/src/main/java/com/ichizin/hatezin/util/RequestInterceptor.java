package com.ichizin.hatezin.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ichizin on 16/03/24.
 *
 * @author ichizin
 */
@Singleton
public class RequestInterceptor implements Interceptor {

    private int MAX_STALE = 30 * 24 * 60 * 60; // 30 days
    private int MAX_AGE = 60 + 5;    // 5min

    private ConnectivityManager connectivityManager;

    @Inject
    public RequestInterceptor(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        if (isConnected()) {
            builder.addHeader("cache-control", "public, max-age=" + MAX_AGE);
        } else {
            builder.addHeader("cache-control", "public, only-if-cached, max-stale=" + MAX_STALE);
        }
        return chain.proceed(builder.build());
    }

    protected boolean isConnected() {
        NetworkInfo networkInfo = this.connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
