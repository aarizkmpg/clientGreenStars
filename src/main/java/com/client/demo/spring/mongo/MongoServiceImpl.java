package com.client.demo.spring.mongo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("mongoService")
public class MongoServiceImpl implements MongoService{

    @Autowired
    @Qualifier("mongoDB")
    protected MongoDatabase mongoDB;

    @Override
    public void insertObject(Object obj, String objectName,String objId){
        ObjectMapper Obj = new ObjectMapper();
        Obj.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Obj.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        try {
            String jsonStr = Obj.writeValueAsString(obj);
            System.out.println(jsonStr);
            Document document2 = mongoDB.getCollection(objectName).find(new BasicDBObject("id",objId)).sort(new BasicDBObject("revision",-1)).limit(1).first();
            long revision = 1l;
            if(document2 != null && document2.get("revision") != null){
                String revisionStr = (String)document2.get("revision");
                revision = Long.parseLong(revisionStr);
                revision = revision +1;
            }
            Document document = new Document("id",objId).append("revision",String.valueOf(revision)).append("audit",jsonStr).append("date",new Date());
            mongoDB.getCollection(objectName).insertOne(document);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<String> listObject(String objectName,String objId){
        List<Document> documents = mongoDB.getCollection(objectName).find(new BasicDBObject("id",objId)).sort(new BasicDBObject("revision",-1)).limit(10).into(new ArrayList<Document>());
        List<String> jsonList = new ArrayList<>();
        documents.forEach(s->{
            System.out.println(s.getString("audit"));
            jsonList.add(s.getString("audit"));
        });
        return jsonList;
    }

    @Override
    public String getObjectById(String objectName, String objId, String revision) {
        List<Document> documents = mongoDB.getCollection(objectName).find(Filters.and(new BasicDBObject("id",objId),new BasicDBObject("revision",revision))).into(new ArrayList<Document>());
        if(documents != null && documents.size()>0){
            return documents.get(0).getString("audit");
        }
        return "";
    }
}
