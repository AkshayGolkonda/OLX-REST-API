package com.olx.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.service.MasterDataService;

@WebMvcTest(MasterDataController.class)
public class MasterDataControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	MasterDataController masterDataController;
	
	@MockBean
	MasterDataService masterDataService;	
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	//Service 6
	@Test
	public void testGetAllCategories() throws Exception {
		List<Category> categoryList=new ArrayList<>();
		categoryList.add(new Category());
		categoryList.add(new Category());
		
		when(this.masterDataService.getAllCategories()).thenReturn(categoryList);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9002/olx-masterdata/advertise/category")
				)
				.andExpect(status().isOk())
				.andReturn();
		
		String  response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("category"),true);
	}
	
	
	//Service 7
	@Test
	public void testGetAllStatus() throws Exception {
		
		List<Status> statusList=new ArrayList<>();
		statusList.add(new Status());
		statusList.add(new Status());
		
		when(this.masterDataService.getAllStatus()).thenReturn(statusList);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9002/olx-masterdata/advertise/status")
				)
				.andExpect(status().isOk())
				.andReturn();
		
		String  response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("status"),true);
	}

}
