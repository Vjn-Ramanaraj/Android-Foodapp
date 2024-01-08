package com.example.resto;

public class DataClass {

    private String DataName;
    private String DataDesc;
    private String Dataprice;

    private String DataImage;
    private String DataCategories;

    private String key;

    public DataClass() {

    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDataName(String dataName) {
        DataName = dataName;
    }

    public void setDataDesc(String dataDesc) {
        DataDesc = dataDesc;
    }

    public void setDataprice(String dataprice) {
        Dataprice = dataprice;
    }

    public void setDataImage(String dataImage) {
        DataImage = dataImage;
    }

    public void setDataCategories(String dataCategories) {
        DataCategories = dataCategories;
    }

    public DataClass(String dataName, String dataDesc, String dataprice, String dataImage, String dataCategories) {
        DataName = dataName;
        DataDesc = dataDesc;
        Dataprice = dataprice;
        DataImage = dataImage;
        DataCategories = dataCategories;
    }

    public String getDataName() {
        return DataName;
    }

    public String getDataDesc() {
        return DataDesc;
    }

    public String getDataprice() {
        return Dataprice;
    }

    public String getDataImage() {
        return DataImage;
    }

    public String getDataCategories() {
        return DataCategories;
    }
}

