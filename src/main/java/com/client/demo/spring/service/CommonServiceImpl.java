package com.client.demo.spring.service;

import com.client.demo.spring.dao.AuditingObj;
import com.client.demo.spring.dao.CommonDao;
import com.client.demo.spring.model.demo.MongoData;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("commonService")
@Transactional(readOnly = false, value="transactionManager")
public class CommonServiceImpl implements CommonService{

    @Autowired
    private CommonDao commonDao;

    @Override
    public <T> T getObjectById(Long id, Class<T> c) {
        return commonDao.getByObjectId(id,c);
    }

    @Override
    public long saveObject(Object obj) {
        return commonDao.saveObject(obj);
    }
    @Override
    public void updateObject(Object obj) {
        commonDao.updateObject(obj);
    }

    @Override
    public void deleteObject(Object obj) {
        commonDao.deleteObject(obj);
    }

    @Override
    public <T> List<T> listObjects(Class T) {
        return commonDao.listObjects(T);
    }

    @Override
    public <T> List<T> listObjects(Class T, int pageNumber) {
        return commonDao.listObjects(T,pageNumber);
    }

    @Override
    public List<AuditingObj> listObjectAuditLogs(Class T, Long id,Integer versionNo) {
        return commonDao.listObjectAuditLogs(T,id,versionNo);
    }

}
