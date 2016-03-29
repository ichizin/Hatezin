package com.ichizin.hatezin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ichizin.hatezin.model.HatenaEntry;

import java.util.List;

/**
 *
 * @author ichizin
 */
public class HotEntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TITLE_ROW = 0;
    public static final int DATA_ROW = 1;

    private Context context;
    private List<HatenaEntry> datas;

    public HotEntryAdapter(Context context, List<HatenaEntry> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TITLE_ROW) {

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(datas.get(position).isTitle()) {
            return TITLE_ROW;
        } else {
            return DATA_ROW;
        }
    }

    /**
     * Data追加
     * @param datas
     */
    public void addData(List<HatenaEntry> datas) {
        datas.addAll(datas);
        this.notifyDataSetChanged();
    }
}
