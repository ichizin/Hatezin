package com.ichizin.hatezin.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import timber.log.Timber;

/**
 *
 *
 * @author ichizin
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable driver;

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public DividerItemDecoration(Context context) {

        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        driver = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();

        for(int i = 0; i < childCount; i++) {

            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + driver.getIntrinsicHeight();
            driver.setBounds(left, top, right, bottom);
            driver.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, driver.getIntrinsicHeight());
    }
}
