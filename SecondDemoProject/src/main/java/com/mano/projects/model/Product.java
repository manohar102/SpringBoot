package com.mano.projects.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products") @Data @NoArgsConstructor	
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="product_generator")
    @SequenceGenerator(name="product_generator", sequenceName = "product_seq")
    private Integer productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Integer productPrice;    
}
