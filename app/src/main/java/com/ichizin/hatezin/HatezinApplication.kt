package com.ichizin.hatezin

import android.app.Application
import com.facebook.stetho.Stetho
import com.ichizin.hatezin.di.component.ApplicationComponent
import com.ichizin.hatezin.di.component.DaggerApplicationComponent
import com.ichizin.hatezin.di.module.ApplicationModule
import timber.log.Timber

/**
 * HatezinApplication

 * @author ichizin
 */
class HatezinApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        // TODO Debugの本番を分けるflavorを使った方が良さそう
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO Crashlyticsでログを送る

        }

        graph = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        graph.inject(this)
    }
}
