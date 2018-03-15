package com.globant.akashdanao.hyperledgerdiamond.data;

import com.globant.akashdanao.hyperledgerdiamond.data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Record;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface DiamondService {
   @GET("/get_all_tuna")
   Flowable<List<Diamond>> getAllDiamonds();

    @POST("/add_tuna")
    @FormUrlEncoded
    Flowable<String> saveRecord(@Field("key") String key, @Field("color") String color, @Field("cut") String cut,
                                @Field("clarity") String clarity, @Field("certification") String certification, @Field("carat") String carat,
                                @Field("name") String name, @Field("holderName") String holdername, @Field("timestamp") String timeStamp,
                                @Field("type") String type, @Field("image") String image);

    @GET
    Flowable<Record> searchRecord(@Url String url);

    @GET
    Flowable<String> changeHolderName (@Url String url);
}