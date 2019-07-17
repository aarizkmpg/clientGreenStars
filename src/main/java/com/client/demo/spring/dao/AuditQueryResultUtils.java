package com.client.demo.spring.dao;

import com.client.demo.spring.model.demo.CustomRevisionEntity;
import org.hibernate.envers.RevisionType;

public class AuditQueryResultUtils {

    private AuditQueryResultUtils() {}

    public static <T> AuditQueryResult<T> getAuditQueryResult(Object[] item, Class<T> type) {

        // Early exit, if no item given:
        if(item == null) {
            return null;
        }

        // Early exit, if there is not enough data:
        if(item.length < 3) {
            return null;
        }

        // Cast item[0] to the Entity:
        T entity = null;
        if(type.isInstance(item[0])) {
            entity = type.cast(item[0]);
        }

        // Then get the Revision Entity:
        CustomRevisionEntity revision = null;
        System.out.println("CustomRevisionEntity class: "+item[1].getClass());
        if(item[1] instanceof CustomRevisionEntity) {
            revision = (CustomRevisionEntity) item[1];
        }

        // Then get the Revision Type:
        RevisionType revisionType = null;
        if(item[2] instanceof RevisionType) {
            revisionType = (RevisionType) item[2];
        }

        // Build the Query Result:
        return new AuditQueryResult<T>(entity, revision, revisionType);
    }
}
