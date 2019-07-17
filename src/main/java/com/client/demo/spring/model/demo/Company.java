package com.client.demo.spring.model.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Audited
@Table(name = "COMPANY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company extends BaseModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "company_id",updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "address")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}