package com.ichizin.hatezin.di.module;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ichizin.hatezin.BuildConfig;
import com.ichizin.hatezin.HatezinApplication;
import com.ichizin.hatezin.di.scope.ApplicationScope;
import com.ichizin.hatezin.util.HttpLoggingInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Application module
 *
 * @author ichizin
 */
@Module
public class ApplicationModule {

    private final HatezinApplication application;

    public ApplicationModule(HatezinApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ApplicationScope
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public Cache provideCache(Context context) {
        long size =  10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), size);
        return cache;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    public Interceptor provideInterceptor(ConnectivityManager connectivityManager) {
        if(BuildConfig.DEBUG) {
            return new HttpLoggingInterceptor(connectivityManager, HttpLoggingInterceptor.Level.BASIC);
        } else {
            return new HttpLoggingInterceptor(connectivityManager, HttpLoggingInterceptor.Level.NONE);
        }
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkhttp(Cache cache, Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache)
                .addInterceptor(interceptor);
        return builder.build();
    }

}
