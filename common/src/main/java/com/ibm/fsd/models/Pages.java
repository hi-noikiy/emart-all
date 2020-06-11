package com.ibm.fsd.models;

import lombok.Data;

@Data
public class Pages {
	
	private int page;
	private int size;
	private String code;
	private String type;
	private String name;
	private String manu;
	private int startPrice;
    private int endPrice;

}
