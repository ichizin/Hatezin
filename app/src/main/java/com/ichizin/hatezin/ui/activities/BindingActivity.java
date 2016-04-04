package com.ichizin.hatezin.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ichizin.hatezin.R;
import com.ichizin.hatezin.databinding.ActivityBindSampleBinding;
import com.ichizin.hatezin.model.User;

/**
 *
 *
 * @author ichizin
 */
public class BindingActivity extends AppCompatActivity {

    private ActivityBindSampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_bind_sample);

        final User user = new User("tsuru", "kazuhito");
        binding.setUser(user);

        Button button = (Button)findViewById(R.id.click_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setLastName("ichizin");
            }
        });
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
}
