package com.client.demo.spring.model.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Audited
@Table(name = "PRODUCTS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product extends BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pro_generator")
    @SequenceGenerator(name="pro_generator", sequenceName = "demo_product_seq", initialValue = 100, allocationSize = 1)
    @Column(name = "product_id",updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "unit_price")
    private long unitPrice;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.LAZY
    )
    @OrderBy("id")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Attribute> attributes = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    private Company company;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

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

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", unitPrice=" + unitPrice +
                ", attributes=" + attributes +
                ", company=" + company +
                '}';
    }
}