package com.globant.akashdanao.hyperledgerdiamond.data;

import com.globant.akashdanao.hyperledgerdiamond.data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Record;
import com.globant.akashdanao.hyperledgerdiamond.utils.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public enum ApiClient {
    instance;

    DiamondService diamondService;

    ApiClient() {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(new StringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        diamondService = retrofit.create(DiamondService.class);

    }

    public Flowable<List<Diamond>> getAllDiamonds() {
        return diamondService.getAllDiamonds();
    }

    public Flowable<String> saveRecord(String id, String holder, long latitude, long longitude, long timeStamp, String vessel) {
        String url = "/add_tuna/" + id + "-" + latitude + "-" + longitude + "-" + timeStamp + "-" + holder + "-" + vessel;
        return diamondService.saveRecord(url);
    }


    // id , color, cut, carat, clarity, certification, name
    public Flowable<String> saveDiamondRecord(String id, String color, String cut, String carat, String clarity, String certification, String name, String holdername) {
        String url = "/add_tuna/" + id + "-" + color + "-" + cut + "-" + carat + "-" + clarity + "-" + certification + "-" + name + "-" + holdername + "-" + (System.currentTimeMillis() / 1000);
        return diamondService.saveRecord(url);
    }

    public Flowable<Record> searchRecord(String recordId) {
        return diamondService.searchRecord("/get_tuna/" + recordId);
    }

    public Flowable<String> changeHolderName(String id, String name) {
        String url = "/change_holder/" + id + "-" + name + "-" + (System.currentTimeMillis() / 1000);
        return diamondService.changeHolderName(url);
    }

    public static class StringConverterFactory extends Converter.Factory {
        private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (String.class.equals(type)) {
                return (Converter<ResponseBody, String>) ResponseBody::string;
            }
            return null;
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            if (String.class.equals(type)) {
                return (Converter<String, RequestBody>) value -> RequestBody.create(MEDIA_TYPE, value);
            }
            return null;
        }
    }
}