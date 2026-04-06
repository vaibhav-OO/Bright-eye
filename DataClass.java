package com.example.bright_eyed;

public class DataClass {
//cafe
    private String dataName;
    private String dataAddress;
    private String dataContact;
    private String dataTiming;
    private String dataRating;
    private String dataImage;
    private String key;
//mall
    private String datamallName;
    private String datamallAdd;
    private String datamallContact;
    private String datamallImage;

    //restro

    private String restroName;
    private String restroAdd;
    private String restroContact;
    private String restroTiming;
    private String restroImage;

    //hospital

    private String hospitalName;
    private String hospitalAdd;
    private String hospitalContact;
    private String hospitalTiming;
    private String hospitalStar;
    private String hospitalSector;
    private String hospitalImage;

    //news

    private String newsName;
    private String newsDetail;
    private String newsImage;

    public String getNewsName() {
        return newsName;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public DataClass(String newsName, String newsDetail, String newsImage) {
        this.newsName = newsName;
        this.newsDetail = newsDetail;
        this.newsImage = newsImage;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalAdd() {
        return hospitalAdd;
    }

    public String getHospitalContact() {
        return hospitalContact;
    }

    public String getHospitalTiming() {
        return hospitalTiming;
    }

    public String getHospitalStar() {
        return hospitalStar;
    }

    public String getHospitalSector() {
        return hospitalSector;
    }

    public String getHospitalImage() {
        return hospitalImage;
    }

    public DataClass(String hospitalName, String hospitalAdd, String hospitalContact, String hospitalTiming, String hospitalStar, String hospitalSector, String hospitalImage) {
        this.hospitalName = hospitalName;
        this.hospitalAdd = hospitalAdd;
        this.hospitalContact = hospitalContact;
        this.hospitalTiming = hospitalTiming;
        this.hospitalStar = hospitalStar;
        this.hospitalSector = hospitalSector;
        this.hospitalImage = hospitalImage;
    }

    public String getRestroName() {
        return restroName;
    }

    public String getRestroAdd() {
        return restroAdd;
    }

    public String getRestroContact() {
        return restroContact;
    }

    public String getRestroTiming() {
        return restroTiming;
    }

    public String getRestroImage() {
        return restroImage;
    }

    public DataClass(String restroName, String restroAdd, String restroContact, String restroTiming, String restroImage) {
        this.restroName = restroName;
        this.restroAdd = restroAdd;
        this.restroContact = restroContact;
        this.restroTiming = restroTiming;
        this.restroImage = restroImage;
    }

    public String getDatamallName() {
        return datamallName;
    }

    public String getDatamallAdd() {
        return datamallAdd;
    }

    public String getDatamallContact() {
        return datamallContact;
    }

    public String getDatamallImage() {
        return datamallImage;
    }

    public DataClass(String datamallName, String datamallAdd, String datamallContact, String datamallImage) {
        this.datamallName = datamallName;
        this.datamallAdd = datamallAdd;
        this.datamallContact = datamallContact;
        this.datamallImage = datamallImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataAddress() {
        return dataAddress;
    }

    public String getDataContact() {
        return dataContact;
    }

    public String getDataTiming() {
        return dataTiming;
    }

    public String getDataRating() {
        return dataRating;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dataName, String dataAddress, String dataContact, String dataTiming, String dataRating, String dataImage) {
        this.dataName = dataName;
        this.dataAddress = dataAddress;
        this.dataContact = dataContact;
        this.dataTiming = dataTiming;
        this.dataRating = dataRating;
        this.dataImage = dataImage;
    }
    public DataClass(){

    }
}
