package com.ichizin.hatezin;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.facebook.stetho.Stetho;
import com.ichizin.hatezin.di.component.ActivityComponent;
import com.ichizin.hatezin.di.component.ApplicationComponent;
import com.ichizin.hatezin.di.component.DaggerApplicationComponent;
import com.ichizin.hatezin.di.component.FragmentComponent;
import com.ichizin.hatezin.di.module.ActivityModule;
import com.ichizin.hatezin.di.module.ApplicationModule;
import com.ichizin.hatezin.di.module.FragmentModule;

import timber.log.Timber;

/**
 * Created by ichizin on 16/03/24.
 *
 * @author ichizin
 */
public class HatezinApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        // TODO Debugの本番を分けるflavorを使った方が良さそう
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO Crashlyticsでログを送る

        }
    }

    @NonNull
    public ApplicationComponent getComponent() {
        return this.applicationComponent;
    }

    @NonNull
    public static ActivityComponent getComponent(AppCompatActivity appCompatActivity) {

        HatezinApplication application = (HatezinApplication)appCompatActivity.getApplication();
        return application.applicationComponent
                .plus(new ActivityModule(appCompatActivity));
    }

    @NonNull
    public static FragmentComponent getComponent(Fragment fragment) {

        assert fragment.getActivity() != null;      // Nullだったら処理中断

        AppCompatActivity activity = (AppCompatActivity)fragment.getActivity();
        HatezinApplication application = (HatezinApplication)fragment.getContext().getApplicationContext();

        return application.applicationComponent
                .plus(new ActivityModule(activity))
                .plus(new FragmentModule(fragment));
    }


}
