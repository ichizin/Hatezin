package com.ichizin.hatezin.di.component;

import com.ichizin.hatezin.di.module.FragmentModule;
import com.ichizin.hatezin.di.scope.PerFragment;
import com.ichizin.hatezin.ui.fragments.EntryDetailFragment;
import com.ichizin.hatezin.ui.fragments.HotEntryFragment;

import dagger.Subcomponent;

/**
 *
 * @author ichizin
 */
@PerFragment
@Subcomponent(modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(HotEntryFragment fragment);
    void inject(EntryDetailFragment fragment);
}
