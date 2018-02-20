package com.globant.akashdanao.hyperledgerdiamond.data;

import com.globant.akashdanao.hyperledgerdiamond.data.Models.Diamond;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DiamondService {
   @GET("/get_all_tuna")
   Flowable<List<Diamond>> getAllDiamonds();

   @GET
   Flowable<String> saveRecord(@Url String url);
}