package com.client.demo.spring.model.demo;

import java.util.Date;

public class MongoData {

    private String id;
    private String json;
    private Date date;

    public MongoData(String id, String json, Date date) {
        this.id = id;
        this.json = json;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
