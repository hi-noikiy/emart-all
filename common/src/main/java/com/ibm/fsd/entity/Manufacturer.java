package com.ibm.fsd.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


@Data
@Entity
@Table(name = "manufacturer")
public class Manufacturer implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "manufacturer_code")
	private String manufacturerCode;
	
	@Column(name = "manufacturer_name")
	private String manufacturerName;

}
