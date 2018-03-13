package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.ui.adapters.SearchAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends Fragment {

    @BindView(R.id.recyclerViewSearchHistory)
    RecyclerView mRecyclerView;
    private View mView;
    @BindView(R.id.editTextSearch)
    EditText editTextSearch;

    private SearchAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.search_fragment, null);
        ButterKnife.bind(this, mView);
        setAdapter();
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        hideKeyBoard();
                        if (!editTextSearch.getText().toString().equalsIgnoreCase("")) {
                            DiamondDetailsFragment fragment = new DiamondDetailsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("RECORD_NUMBER", editTextSearch.getText().toString());
                            fragment.setArguments(bundle);
                            final FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.fl_home, fragment, fragment.getClass().getName());
                            ft.commit();
                        }
                        break;
                }
                return false;
            }
        });
        return mView;
    }

    private void setAdapter() {
        adapter = new SearchAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);

    }


    private void getSearchHistory() {
        adapter.setData(null);
    }

    public void hideKeyBoard() {
        if (mView != null) {
            InputMethodManager imm = (InputMethodManager) mView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
        }
    }

}
