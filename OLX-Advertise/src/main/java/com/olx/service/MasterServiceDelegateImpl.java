package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MasterServiceDelegateImpl implements MasterDataServiceDelegate {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public String getCategoryValue(int id) {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		ResponseEntity<String> response=this.restTemplate.getForEntity("http://localhost:9002/olx-masterdata/advertise/category/{id}", String.class,id);
		return response.getBody();
	}

	@Override
	public String getStatusValue(int id) {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		ResponseEntity<String> response=this.restTemplate.getForEntity("http://localhost:9002/olx-masterdata/advertise/status/{id}", String.class,id);
		return response.getBody();
		}
}
