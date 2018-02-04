package com.globant.akashdanao.hyperledgerdiamond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.globant.akashdanao.hyperledgerdiamond.Data.ApiClient;
import com.globant.akashdanao.hyperledgerdiamond.Data.Models.Diamond;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}