package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.ui.Adapters.SearchAdapter;


public class SearchFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View mView;

    private SearchAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.search_fragment, null);
        initView();
        setAdapter();
        return mView;
    }

    private void setAdapter() {
        adapter = new SearchAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);

    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerViewSearchHistory);
    }

    private void getSearchHistory() {
        adapter.setData(null);
    }

}
