package com.ibm.fsd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "product")
@DynamicUpdate
@NoArgsConstructor
public class Product implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "discount")
	private double discount;
	
	@Column(name = "sales_volume")
	private int salesVolume;
	
	@Column(name = "active")
	private String active;
	
	@Column(name = "category_code")
	private String categoryCode;
	
	@Column(name = "manufacturer_code")
	private String manufacturerCode;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@CreationTimestamp
	@Column(name = "create_time")
    private Date createTime;
    
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

}
