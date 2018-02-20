package com.globant.akashdanao.hyperledgerdiamond.data.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diamond {

    @SerializedName("Key")
    @Expose
    private String key;

    @SerializedName("Record")
    @Expose
    private Record record;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}