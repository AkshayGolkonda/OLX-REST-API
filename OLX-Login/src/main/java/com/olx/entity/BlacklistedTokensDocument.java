package com.olx.entity;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="blacklistedtokens")
public class BlacklistedTokensDocument {
	@Id
	private int id;
	private String token;
	private LocalDate date;
	public BlacklistedTokensDocument(int id, String token, LocalDate date) {
		super();
		this.id = id;
		this.token = token;
		this.date = date;
	}
	
	public BlacklistedTokensDocument(String token, LocalDate date) {
		super();
		this.token = token;
		this.date = date;
	}

	public BlacklistedTokensDocument() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BlacklistedTokensDocument [id=" + id + ", token=" + token + ", date=" + date + "]";
	}

}
