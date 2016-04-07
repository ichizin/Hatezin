package com.ichizin.hatezin.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.support.customtabs.CustomTabsIntent;

import com.ichizin.hatezin.R;
import com.ichizin.hatezin.ui.activities.BindingActivity;
import com.ichizin.hatezin.ui.activities.MainActivity;
import com.ichizin.hatezin.ui.fragments.EntryDetailFragment;

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

    public void web(Activity activity, String url) {

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(activity.getResources().getColor(R.color.colorPrimary)).setShowTitle(true);
//        builder.setStartAnimations(context, R.anim.slide)

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(activity, Uri.parse(url));
    }

    public void entryReadMore(MainActivity activity, HatenaCategory hatenaCategory) {

        EntryDetailFragment fragment = EntryDetailFragment.newInstance(hatenaCategory);
        activity.replaceFragemnt(fragment, true);
    }

}
