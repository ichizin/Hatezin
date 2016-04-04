package com.ichizin.hatezin.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ichizin.hatezin.HatezinApplication;
import com.ichizin.hatezin.R;
import com.ichizin.hatezin.ui.fragments.HotEntryFragment;
import com.ichizin.hatezin.util.Navigator;

import javax.inject.Inject;

/**
 *
 *
 * @author ichizin
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        HatezinApplication.getComponent(this).inject(this);

        setFragment();

//        Button button = (Button)findViewById(R.id.binding_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigator.bindingSample(MainActivity.this);
//            }
//        });

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
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
