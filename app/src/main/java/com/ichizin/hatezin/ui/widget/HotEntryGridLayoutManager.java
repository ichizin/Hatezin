package com.ichizin.hatezin.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;


import com.ichizin.hatezin.ui.adapter.HotEntryAdapter;

/**
 * HotEntryGridLayoutManager
 *
 * @author ichizin
 */
public class HotEntryGridLayoutManager extends GridLayoutManager {

    public HotEntryGridLayoutManager(@NonNull Context context,
                                     @NonNull final int spanCount,
                                     @NonNull final HotEntryAdapter adapter) {
        super(context, spanCount);

        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {

                if(adapter.getItemViewType(position) == HotEntryAdapter.TITLE_ROW) {
                    return spanCount;
                }
                return 1;
            }

            @Override
            public int getSpanIndex(int position, int spanCount) {

                if(adapter.getItemViewType(position) == HotEntryAdapter.TITLE_ROW) {
                    return 0;
                }
                return 1;
            }
        });

    }
}
