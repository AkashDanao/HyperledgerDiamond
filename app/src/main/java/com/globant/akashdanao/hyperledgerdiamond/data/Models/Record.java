
package com.globant.akashdanao.hyperledgerdiamond.data.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("carat")
    @Expose
    private String carat;
    @SerializedName("cert")
    @Expose
    private String cert;
    @SerializedName("clarity")
    @Expose
    private String clarity;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("cut")
    @Expose
    private String cut;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("transid")
    @Expose
    private String transid;

    public String getCarat() {
        return carat;
    }

    public void setCarat(String carat) {
        this.carat = carat;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getClarity() {
        return clarity;
    }

    public void setClarity(String clarity) {
        this.clarity = clarity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

}
