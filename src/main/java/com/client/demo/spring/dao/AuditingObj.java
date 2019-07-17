package com.client.demo.spring.dao;

import org.hibernate.envers.RevisionType;

public class AuditingObj {

    private final Object object;
    private final Number revision;
    private final RevisionType revisionType;


    public AuditingObj(Object obj, Number revision, RevisionType revisionType) {
        this.object = obj;
        this.revision = revision;
        this.revisionType = revisionType;
    }
    public Object getObject() {
        return object;
    }

    public Number getRevision() {
        return revision;
    }

    public RevisionType getRevisionType() {
        return revisionType;
    }


}
