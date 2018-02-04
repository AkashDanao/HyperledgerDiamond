package com.globant.akashdanao.hyperledgerdiamond.UI.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.globant.akashdanao.hyperledgerdiamond.Data.ApiClient;
import com.globant.akashdanao.hyperledgerdiamond.Data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.UI.Adapters.DiamondAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.rv_diamond)
    RecyclerView recyclerViewDiamond;
    private DiamondAdapter diamondAdapter;
    private List<Diamond> diamondList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        ApiClient.getInstance().getAllDiamonds().enqueue(new Callback<List<Diamond>>() {
            @Override
            public void onResponse(Call<List<Diamond>> call, Response<List<Diamond>> response) {
                Log.d(TAG, "onResponse: " + response.toString());
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
        diamondAdapter.notifyDataSetChanged();
    }
}
