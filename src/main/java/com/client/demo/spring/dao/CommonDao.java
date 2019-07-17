package com.client.demo.spring.dao;

import com.client.demo.spring.model.demo.*;

import java.util.List;

public interface CommonDao {

    <T> T getByObjectId(Long id, Class<T> t);

    long saveObject(Object obj);
    void updateObject(Object obj);
    void deleteObject(Object obj);
    <T> List<T> listObjects(Class T);
    <T> List<T> listObjects(Class T,int pageNumber);

    <T> List<AuditingObj> listObjectAuditLogs(Class T, Long id, Integer versionNo);

}
