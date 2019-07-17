package com.client.demo.spring.model.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Audited
@Table(name = "ATTRIBUTE")
public class Attribute implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "attribute_id",updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Transient
    private int deleting = 0;  // 1 = deleting


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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute )) return false;
        return id != null && id.equals(((Attribute) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }

    public int getDeleting() {
        return deleting;
    }

    public void setDeleting(int deleting) {
        this.deleting = deleting;
    }
}