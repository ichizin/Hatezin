package com.ichizin.hatezin.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ichizin.hatezin.BR;
import com.ichizin.hatezin.R;
import com.ichizin.hatezin.model.HatenaEntry;

import java.util.List;

/**
 *
 *
 * @author ichizin
 */
public class EntryDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface EntryDetailAdapterListener {
        void onClickItem(String url);
    }

    private Context context;
    private List<HatenaEntry> entries;
    private EntryDetailAdapterListener listener;

    public EntryDetailAdapter(Context context, List<HatenaEntry> entries) {
        this.context = context;
        this.entries = entries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_hot_entry_data, parent, false);

        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final DataViewHolder dataViewHolder = (DataViewHolder)holder;
        dataViewHolder.getBinding().setVariable(BR.hatena, entries.get(position));
        LinearLayout content = (LinearLayout)dataViewHolder.getBinding().getRoot().findViewById(R.id.hot_content);

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClickItem(entries.get(position).getLink());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void add(List<HatenaEntry> entries) {

        this.entries.addAll(entries);
        this.notifyDataSetChanged();
    }

    public void clear() {

        this.entries.clear();
        this.notifyDataSetChanged();
    }

    public void setEntryDetailAdapterListener(EntryDetailAdapterListener listener) {
        this.listener = listener;
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
