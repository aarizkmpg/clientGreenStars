package com.client.demo.spring.controller;

import com.client.demo.spring.model.demo.Company;
import com.client.demo.spring.model.demo.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.Arrays;

public class TestSingleton {

    private static TestSingleton instance;

    private TestSingleton(){

    }

    public TestSingleton getInstance(){

        if(instance == null){
            instance = new TestSingleton();
        }

        return instance;


    }

    public static void main(String[] args) {
    /*
        MongoClient mongoClient = null;
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("admin", "admin",
                "password".toCharArray());

        mongoClient = new MongoClient(new ServerAddress("ec2-52-15-73-141.us-east-2.compute.amazonaws.com", 27017), Arrays.asList(mongoCredential));

        DB db = mongoClient.getDB("client");

        System.out.println(db.getStats());
        System.out.println(db.getCollectionNames());

        mongoClient.close();
        */

        ObjectMapper Obj = new ObjectMapper();
        Obj.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Obj.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        Product obj = new Product();
        obj.setId(111l);
        Company company = new Company();
        company.setId(222l);
        obj.setCompany(company);

        String jsonStr = null;
        try {
            jsonStr = Obj.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonStr);
    }

}
