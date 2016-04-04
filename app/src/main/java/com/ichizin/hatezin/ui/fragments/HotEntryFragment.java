package com.ichizin.hatezin.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ichizin.hatezin.HatezinApplication;
import com.ichizin.hatezin.R;
import com.ichizin.hatezin.model.HatenaEntry;
import com.ichizin.hatezin.presenter.HotEntryPresenter;
import com.ichizin.hatezin.ui.adapter.HotEntryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 *
 *
 * @author ichizin
 */
public class HotEntryFragment extends BaseFragment implements HotEntryPresenter.HotEntryView {

    @Inject
    HotEntryPresenter presenter;

    @Bind(R.id.fragment_recycler_content)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private HotEntryAdapter hotEntryAdapter;

    public static HotEntryFragment newInstance() {
        return new HotEntryFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        HatezinApplication.getComponent(this).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            presenter.attachView(this);
            presenter.initialize();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void renderData(List<HatenaEntry> feeds) {
        hotEntryAdapter.addData(feeds);
    }

    /**
     *
     */
    private void initUI() {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        hotEntryAdapter = new HotEntryAdapter(getContext(), new ArrayList<HatenaEntry>());
        recyclerView.setAdapter(hotEntryAdapter);
    }
}
