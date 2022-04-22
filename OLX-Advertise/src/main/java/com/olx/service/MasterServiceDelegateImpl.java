package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MasterServiceDelegateImpl implements MasterDataServiceDelegate {
	
	@Autowired
	RestTemplate restTemplate;

	@CircuitBreaker(name="CATEGORY_STATUS_MAPPING",fallbackMethod ="fallbackgetCategoryValue")
	@Override
	public String getCategoryValue(int id) {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		ResponseEntity<String> response=this.restTemplate.getForEntity("http://API-GATEWAY/olx-masterdata/advertise/category/{id}", String.class,id);
		return response.getBody();
	}

	@CircuitBreaker(name="CATEGORY_STATUS_MAPPING",fallbackMethod ="fallbackgetStatusValue")
	@Override
	public String getStatusValue(int id) {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		ResponseEntity<String> response=this.restTemplate.getForEntity("http://API-GATEWAY/olx-masterdata/advertise/status/{id}", String.class,id);
		return response.getBody();
	}
	
	public String fallbackgetCategoryValue(int id,Exception exception) {
		System.out.println("OLX-MASTERDATA failed -- Inside fallbackgetCategoryValue "+exception);
		return null;
	}
	
	public String fallbackgetStatusValue(int id,Exception exception) {
		System.out.println("OLX-MASTERDATA failed -- Inside fallbackgetStatusValue "+exception);
		return null;
	}
}
