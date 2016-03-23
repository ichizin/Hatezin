package com.ichizin.hatezin.di.component;

import com.ichizin.hatezin.HatezinApplication;
import com.ichizin.hatezin.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ichizin on 16/03/23.
 *
 * @author ichizin
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(HatezinApplication hatezinApplication);
}
