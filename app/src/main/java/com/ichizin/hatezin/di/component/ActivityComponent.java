package com.ichizin.hatezin.di.component;

import com.ichizin.hatezin.di.module.ActivityModule;
import com.ichizin.hatezin.di.module.FragmentModule;
import com.ichizin.hatezin.di.scope.PerActivity;
import com.ichizin.hatezin.ui.activities.MainActivity;

import dagger.Subcomponent;

/**
 *  SubComponent化することによって親コンポーネントののインナークラスで定義される
 *  SubComponentを使用するには子コンポーネントの指定を明示する必要がある
 *
 * @author ichizin
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);

    FragmentComponent plus(FragmentModule fragmentModule);
}
