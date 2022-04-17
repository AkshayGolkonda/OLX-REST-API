package com.olx.dto;

import java.time.LocalDate;

public class Advertise {
	
	private int id;
	private String title;
	private String description;
	private int price;
	private int categoryId;
	private int statusId;
	private String category;
	private String status;
	private String username;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	public Advertise(int id, String title, String description, int price, int categoryId, int statusId, String category,
			String status, String username, LocalDate createdDate, LocalDate modifiedDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.category = category;
		this.status = status;
		this.username = username;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	public Advertise() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "Advertise [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", categoryId=" + categoryId + ", statusId=" + statusId + ", category=" + category + ", status="
				+ status + ", username=" + username + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ "]";
	}
	
	
	
}
