package com.ichizin.hatezin.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ichizin.hatezin.R;

/**
 *
 *
 * @author ichizin
 */
public final class ViewBindingUtils {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).placeholder(R.color.placeholder).into(view);
    }

    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).placeholder(R.color.placeholder).error(error).into(view);
    }
}
