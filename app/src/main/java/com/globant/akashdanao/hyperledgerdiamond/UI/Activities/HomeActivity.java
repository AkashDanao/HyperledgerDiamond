package com.globant.akashdanao.hyperledgerdiamond.UI.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.globant.akashdanao.hyperledgerdiamond.Data.ApiClient;
import com.globant.akashdanao.hyperledgerdiamond.Data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.UI.Adapters.DiamondAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.rv_diamond)
    RecyclerView recyclerViewDiamond;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private DiamondAdapter diamondAdapter;
    private List<Diamond> diamondList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();

        ApiClient.getInstance().getAllDiamonds().enqueue(new Callback<List<Diamond>>() {
            @Override
            public void onResponse(Call<List<Diamond>> call, Response<List<Diamond>> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                Log.d(TAG, "onResponse: body  " + response.body());
                Log.d(TAG, "onResponse: code "  + response.code());
                Log.d(TAG, "onResponse: message " + response.message());
                diamondList.addAll(new ArrayList<Diamond>());
                diamondAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Diamond>> call, Throwable t) {
                Log.d(TAG, "onfailure: " + t.toString());
                t.printStackTrace();

            }
        });
    }

    public void init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDiamond.setLayoutManager(layoutManager);
        diamondAdapter = new DiamondAdapter(diamondList);
        recyclerViewDiamond.setAdapter(diamondAdapter);
    }

    @OnClick(R.id.fab)
    public void onClickFab(View v){
        startActivity(new Intent(this, AddDiamondActivity.class));
    }

}
