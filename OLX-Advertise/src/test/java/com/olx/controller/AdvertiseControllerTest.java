package com.olx.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.Advertise;
import com.olx.service.AdvertiseService;

@WebMvcTest(AdvertiseController.class)
public class AdvertiseControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AdvertiseController advertiseController;
	
	@MockBean
	AdvertiseService advertiseService;	
	
	@Autowired
	ObjectMapper objectMapper;
	
	//Service 8
	@Test
	public void testCreateNewAdvertise() throws Exception{
		
		Advertise advertise=new Advertise();
		advertise.setTitle("Sofa Sale");
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.advertiseService.createNewAdvertise(advertise, "AG66200")).thenReturn(advertise);
		
		MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:9001/olx-adv/advertise")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(advertise))
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Sofa")))
				.andReturn();
		
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Sofa"),true);
	}
	
	//Service 9
	@Test
	public void testUpdateAdvertise() throws Exception{
		
		Advertise advertise=new Advertise();
		advertise.setTitle("Sofa Sale");
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.advertiseService.updateAdvertise(1,advertise, "AG66200")).thenReturn(advertise);
		
		MvcResult mvcResult=this.mockMvc.perform(put("http://localhost:9001/olx-adv/advertise/"+1)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(advertise))
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Sofa")))
				.andReturn();
		
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Sofa"),true);
	}
	
	//Service 10
	@Test
	public void testgetAllAdvByUser() throws Exception {
		List<Advertise> advertiseList=new ArrayList<>();
		advertiseList.add(new Advertise());
		advertiseList.add(new Advertise());
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.advertiseService.getAllAdvByUser("AG66200")).thenReturn(advertiseList);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9001/olx-adv/user/advertise")
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andReturn();
		
		String  response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("title"),true);
	}
	
	//Service 11
	@Test
	public void testGetAdvById() throws Exception{
		
		Advertise advertise=new Advertise();
		advertise.setTitle("Sofa Sale");
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.advertiseService.getAdvById(1, "AG66200")).thenReturn(advertise);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9001/olx-adv/user/advertise/"+1)
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Sofa")))
				.andReturn();
		
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Sofa"),true);
	}
	
	
	//Service 12
	@Test
	public void testDeleteAdvById() throws Exception{
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.advertiseService.deleteAdvById(1, "AG66200")).thenReturn(true);
		
		MvcResult mvcResult=this.mockMvc.perform(delete("http://localhost:9001/olx-adv/user/advertise/"+1)
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andReturn();
		
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("true"),true);
	}
	
	//Service 13
	@Test
	public void testSearchAdvertisesByFilterCriteria() throws Exception {
		List<Advertise> advertiseList=new ArrayList<>();
		advertiseList.add(new Advertise());
		advertiseList.add(new Advertise());
		when(this.advertiseService.searchAdvertisesByFilterCriteria(null, "Furniture", null, null, null, null, null, null, 0, 10))
		.thenReturn(advertiseList);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9001/olx-adv/advertise/search/filtercriteria")
				.param("category", "Furniture").param("startIndex", "0"))
				.andExpect(status().isOk())
				.andReturn();
		
		String  response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("title"),true);
	}
	
	
	//Service 14
	@Test
	public void testSearchAdvByText() throws Exception {
		List<Advertise> advertiseList=new ArrayList<>();
		advertiseList.add(new Advertise());
		advertiseList.add(new Advertise());
		when(this.advertiseService.SearchAdvByText("sofa")).thenReturn(advertiseList)
		.thenReturn(advertiseList);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9001/olx-adv/advertise/search")
				.param("searchText", "sofa")
				)
				.andExpect(status().isOk())
				.andReturn();
		
		String  response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("title"),true);
	}
	
	//Service 15
	@Test
	public void testReturnAdv() throws Exception{
		
		Advertise advertise=new Advertise();
		advertise.setTitle("Sofa Sale");
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.advertiseService.returnAdv(1, "AG66200")).thenReturn(advertise);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9001/olx-adv/advertise/"+1)
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Sofa")))
				.andReturn();
		
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Sofa"),true);
	}

	

}
