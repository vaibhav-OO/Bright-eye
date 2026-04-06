package com.example.bright_eyed;

public class DataCafeClass {

    private String datacoupencode;
    private String datacoupendetail;
    private String datacoupendiscount;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDatacoupencode() {
        return datacoupencode;
    }

    public String getDatacoupendetail() {
        return datacoupendetail;
    }

    public String getDatacoupendiscount() {
        return datacoupendiscount;
    }

    public DataCafeClass(String datacoupencode, String datacoupendetail, String datacoupendiscount) {
        this.datacoupencode = datacoupencode;
        this.datacoupendetail = datacoupendetail;
        this.datacoupendiscount = datacoupendiscount;

    }
    public DataCafeClass(){

    }
}
