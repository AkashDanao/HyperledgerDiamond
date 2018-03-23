
package com.globant.akashdanao.hyperledgerdiamond.data.Models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diamond implements Comparable<Diamond> {

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

    @Override
    public int compareTo(@NonNull Diamond diamond) {
        return -this.getRecord().getTimeStamp().compareTo(diamond.getRecord().getTimeStamp());
    }
}
