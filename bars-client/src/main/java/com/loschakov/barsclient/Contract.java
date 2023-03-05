package com.loschakov.barsclient;


import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;

public class Contract {

    private String isRelevance;
    private String name;
    private String updateDate;
    private String createDate;

    public Contract(String isRelevance, String name, String updateDate, String createDate) {
        this.isRelevance = isRelevance;
        this.name = name;
        this.updateDate = updateDate;
        this.createDate = createDate;
    }

    public Contract() {
    }

    public String getIsRelevance() {
        return isRelevance;
    }

    public void setIsRelevance(String isRelevance) {
        this.isRelevance = isRelevance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
