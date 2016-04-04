package com.ichizin.hatezin.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ichizin.hatezin.BR;
import com.ichizin.hatezin.R;
import com.ichizin.hatezin.model.HatenaEntry;

import java.util.List;

/**
 *
 * @author ichizin
 */
public class HotEntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface HotEntryAdapterListener {

    }

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
            View v = LayoutInflater.from(context).inflate(R.layout.adapter_hot_entry_header, parent, false);
            return new TitleViewHolder(v);
        }

        View v = LayoutInflater.from(context).inflate(R.layout.adapter_hot_entry_data, parent, false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == TITLE_ROW) {
            TitleViewHolder titleHolder = (TitleViewHolder)holder;
            titleHolder.getBinding().setVariable(BR.hatena, datas.get(position));
        }  else {
            DataViewHolder dataViewHolder = (DataViewHolder)holder;
            dataViewHolder.getBinding().setVariable(BR.hatena, datas.get(position));
        }
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
     * @param feeds
     */
    public void addData(List<HatenaEntry> feeds) {
        datas.addAll(feeds);
        this.notifyDataSetChanged();
    }

    /**
     * TitleViewHolder
     */
    public static class TitleViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public TitleViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    /**
     * Data ViewHolder
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public DataViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

}
