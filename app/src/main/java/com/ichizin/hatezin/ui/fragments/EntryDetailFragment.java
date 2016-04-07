package com.ichizin.hatezin.ui.fragments;

import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ichizin.hatezin.HatezinApplication;
import com.ichizin.hatezin.R;
import com.ichizin.hatezin.model.HatenaEntry;
import com.ichizin.hatezin.presenter.EntryDetailPresenter;
import com.ichizin.hatezin.ui.adapter.EntryDetailAdapter;
import com.ichizin.hatezin.util.HatenaCategory;
import com.ichizin.hatezin.util.Navigator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 *
 *
 * @author ichizin
 */
public class EntryDetailFragment extends BaseFragment
        implements EntryDetailPresenter.EntryDetailView, EntryDetailAdapter.EntryDetailAdapterListener {

    private static final String BUNDLE_KEY = "hatena_category";

    @Inject
    EntryDetailPresenter presenter;

    @Bind(R.id.fragment_recycler_content)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private HatenaCategory hatenaCategory;
    private EntryDetailAdapter entryDetailAdapter;

    public static EntryDetailFragment newInstance(HatenaCategory hatenaCategory) {

        EntryDetailFragment fragment = new EntryDetailFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY, hatenaCategory.getCategory());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        HatezinApplication.getComponent(this).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.hatenaCategory = HatenaCategory.valueOfId(getArguments().getString(BUNDLE_KEY));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState == null) {
            initUI();
            initPresenter();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initPresenter() {
        presenter.attachView(this);
        presenter.getData(hatenaCategory);
    }

    private void initUI() {

        entryDetailAdapter = new EntryDetailAdapter(getContext(), new ArrayList<HatenaEntry>());
        entryDetailAdapter.setEntryDetailAdapterListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(entryDetailAdapter);
    }

    @Override
    public void renderData(List<HatenaEntry> datas) {
        entryDetailAdapter.add(datas);
    }

    @Override
    public void onClickItem(String url) {
        navigator.web(getActivity(), url);
    }
}
