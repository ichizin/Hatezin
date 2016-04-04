package com.ichizin.hatezin.di.module;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ichizin.hatezin.BuildConfig;
import com.ichizin.hatezin.HatezinApplication;
import com.ichizin.hatezin.util.Navigator;
import com.ichizin.hatezin.util.RequestInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
        return new RequestInterceptor(connectivityManager);
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkhttp(Cache cache, Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor);
        if(BuildConfig.DEBUG) {
            builder.addInterceptor(
                    new okhttp3.logging.HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    @Provides
    @Singleton
    public Navigator provideNavigator() {
        return new Navigator();
    }

}
