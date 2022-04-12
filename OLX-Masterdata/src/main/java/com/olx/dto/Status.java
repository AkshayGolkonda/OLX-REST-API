package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="MasterData DTO")
public class Status {
	
	@ApiModelProperty(value="MasterData identifier")
	private int id;
	@ApiModelProperty(value="MasterData status")
	private String status;
	
	public Status(int id, String status) {
	super();
	this.id = id;
	this.status = status;
	}
	public Status() {
	super();
	}
	public int getId() {
	return id;
	}
	public void setId(int id) {
	this.id = id;
	}
	public String getStatus() {
	return status;
	}
	public void setStatus(String status) {
	this.status = status;
	}
	@Override
	public String toString() {
	return "Status [id=" + id + ", status=" + status + "]";
	}

}
