package com.ichizin.hatezin.di.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 * @author ichizin
 */
@Module
public class FragmentModule {

    final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public Context provideContext() {
        return fragment.getContext();
    }
}
