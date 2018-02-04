package com.globant.akashdanao.hyperledgerdiamond.Data.Models;

/**
 * Created by akash.danao on 02/02/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("holder")
    @Expose
    private String holder;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("vessel")
    @Expose
    private String vessel;

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel;
    }

}



