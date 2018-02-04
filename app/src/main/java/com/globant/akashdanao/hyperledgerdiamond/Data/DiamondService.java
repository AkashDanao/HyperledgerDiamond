package com.globant.akashdanao.hyperledgerdiamond.Data;

import com.globant.akashdanao.hyperledgerdiamond.Data.Models.Diamond;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by akash.danao on 02/02/18.
 */

public interface DiamondService {

   @GET("/get_all_tuna")
   Call<List<Diamond>> getAllDiamonds();
}
