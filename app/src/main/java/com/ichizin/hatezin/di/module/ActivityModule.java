package com.ichizin.hatezin.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 *
 * @author ichizin
 */
@Module
public class ActivityModule {

    private final AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    public Activity activity() {
        return appCompatActivity;
    }

    @Provides
    public Context context() {
        return appCompatActivity;
    }
}
