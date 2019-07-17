package com.client.demo.spring.model.demo;

import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.DateFormat;

@Entity
@RevisionEntity
@Table(name = "revinfo")
public class SampleRevisionEntity extends CustomRevisionEntity {

    public SampleRevisionEntity() {
    }

    public String toString() {
        return "SampleRevisionEntity(revisionNumber = " + getRevisionNumber() + ", revisionDate = " + DateFormat.getDateTimeInstance().format(this.getRevisionDate()) + ")";
    }
}
