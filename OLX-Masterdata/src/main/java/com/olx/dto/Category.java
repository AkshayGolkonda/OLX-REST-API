package com.olx.dto;

import io.swagger.annotations.ApiModelProperty;

public class Category {

	@ApiModelProperty(value="MasterData identifier")
	private int id;
	@ApiModelProperty(value="MasterData categories")
	private String category;
	public Category(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}
	public Category() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + "]";
	}
	
}
