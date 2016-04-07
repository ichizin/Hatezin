package com.ichizin.hatezin.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ichizin.hatezin.HatezinApplication;
import com.ichizin.hatezin.R;
import com.ichizin.hatezin.ui.fragments.BaseFragment;
import com.ichizin.hatezin.ui.fragments.HotEntryFragment;
import com.ichizin.hatezin.util.Navigator;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 *
 * @author ichizin
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        HatezinApplication.getComponent(this).inject(this);

        setToolbar();
        setFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setFragment() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HotEntryFragment fragment = HotEntryFragment.newInstance();

        transaction.add(R.id.main_content, fragment);
        transaction.commit();
    }

    private void setToolbar() {
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        toolbar.setTitle(getResources().getString(R.string.app_name));

    }

    public void replaceFragemnt(BaseFragment fragment, boolean isBackStack) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_content, fragment);
        if(isBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
