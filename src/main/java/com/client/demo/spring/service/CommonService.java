package com.client.demo.spring.service;

import com.client.demo.spring.dao.AuditingObj;
import com.client.demo.spring.model.demo.*;

import java.util.List;

public interface CommonService {

   <T> T getObjectById(Long id, Class<T> c);
   long saveObject(Object obj);
   void updateObject(Object obj);
   void deleteObject(Object obj);
   <T> List<T> listObjects(Class T);
   <T> List<T> listObjects(Class T,int pageNumber);

   List<AuditingObj> listObjectAuditLogs(Class T, Long id,Integer versionNo);
}
