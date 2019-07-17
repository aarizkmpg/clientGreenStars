package com.client.demo.spring.mongo;

import java.util.List;

public interface MongoService {

    void insertObject(Object obj, String objectName,String objId);
    List<String> listObject(String objectName, String objId);
    String getObjectById(String objectName, String objId,String revision);
}
