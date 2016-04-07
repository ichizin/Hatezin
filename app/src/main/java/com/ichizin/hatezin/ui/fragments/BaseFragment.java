package com.ichizin.hatezin.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ichizin.hatezin.util.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 *
 *
 * @author ichizin
 */
public class BaseFragment extends Fragment {

    @Inject
    Navigator navigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
