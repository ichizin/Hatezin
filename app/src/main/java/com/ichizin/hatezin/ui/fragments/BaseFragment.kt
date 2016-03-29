package com.ichizin.hatezin.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

import butterknife.ButterKnife

/**
 * BaseFragment
 * @author ichizin
 */
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.unbind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
