package com.globant.akashdanao.hyperledgerdiamond.Data;

import com.globant.akashdanao.hyperledgerdiamond.Data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.Utils.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by akash.danao on 02/02/18.
 */

public class ApiClient {
    public static ApiClient mInstance;
    DiamondService diamondService;

    public ApiClient(){
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
        diamondService = retrofit.create(DiamondService.class);

    }

    public static ApiClient getInstance(){
        if (mInstance == null){
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    public Call<List<Diamond>> getAllDiamonds(){
        return diamondService.getAllDiamonds();
    }


}
