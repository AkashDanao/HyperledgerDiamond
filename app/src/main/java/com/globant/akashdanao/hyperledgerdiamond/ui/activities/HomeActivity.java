package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Record;
import com.globant.akashdanao.hyperledgerdiamond.ui.Adapters.RecordsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
    String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.rv_diamond)
    RecyclerView recyclerViewDiamond;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private RecordsAdapter recordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ApiClient.instance.getAllDiamonds()
                .flatMap(Flowable::fromIterable)
                .map(Diamond::getRecord)
                .toList()
                .toFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bindDataToRecyclerView, this::handleError);
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDiamond.setLayoutManager(layoutManager);
    }

    private void bindDataToRecyclerView(List<Record> records) {
        recordsAdapter = new RecordsAdapter(records);
        recyclerViewDiamond.setAdapter(recordsAdapter);
    }

    private void handleError(Throwable throwable) {

    }

    @OnClick(R.id.fab)
    public void onClickFab(View v) {
        startActivity(new Intent(this, AddDiamondActivity.class));
    }
}
