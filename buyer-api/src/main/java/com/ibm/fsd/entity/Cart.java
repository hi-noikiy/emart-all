package com.ibm.fsd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@Table(name = "cart")
public class Cart implements Serializable {
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "product_code")
	private String productCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "discount")
	private double discount;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "user_id")
	private int userId;
	
	@CreationTimestamp
	@Column(name = "create_time")
    private Date createTime;
    
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;
	
}
