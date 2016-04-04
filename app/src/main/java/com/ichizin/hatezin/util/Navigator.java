package com.ichizin.hatezin.util;

import android.content.Context;
import android.content.Intent;

import com.ichizin.hatezin.ui.activities.BindingActivity;

/**
 *
 * @author ichizin
 */
public class Navigator {

    public void bindingSample(Context context) {

        Intent intent = new Intent(context,  BindingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
