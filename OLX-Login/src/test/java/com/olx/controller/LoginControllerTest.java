package com.olx.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.User;
import com.olx.service.LoginService;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	LoginController loginController;
	
	@MockBean
	LoginService loginService;	
	
	@Autowired
	ObjectMapper objectMapper;
	
	//Service 1
	@Test
	public void testAuthenticate() throws Exception{
		User user=new User();
		user.setUserName("akshaygolkonda");
		user.setPassword("akshay123");
		
		when(this.loginService.authenticate(user)).thenReturn(user.getUserName());
		
		MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:9000/olx-login/user/authenticate")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user))
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("akshay")))
				.andReturn();
		
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("akshay"),true);
	}
	
	//Service 2
	@Test
	public void testLogout() throws Exception{
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.loginService.logout("AG66200")).thenReturn(true);
		
		MvcResult mvcResult=this.mockMvc.perform(delete("http://localhost:9000/olx-login/user/logout")
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andReturn();
		
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("true"),true);
	}
	
	
	//Service 3
	@Test
	public void testAddUser() throws Exception{
		
		User user=new User();
		user.setUserName("abhishekgolkonda");
		user.setPassword("abhishek123");
		
		when(this.loginService.addUser(user)).thenReturn(user);
		
		MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:9000/olx-login/user")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user))
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("abhishek")))
				.andReturn();
		
		String response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("abhishek"),true);
	}
	
	//Service 4
	@Test
	public void testGetUser() throws Exception {
		
		User user=new User();
		user.setUserName("abhishekgolkonda");
		user.setPassword("abhishek123");
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.loginService.getUser("AG66200")).thenReturn(user);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9000/olx-login/user")
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("abhishek")))
				.andReturn();
		
		String  response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("abhishek"),true);
	}
	
	//Service 5
	@Test
	public void testValidateJWT() throws Exception {
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("Authorization", "AG66200");
		
		when(this.loginService.validateJWT("AG66200")).thenReturn(true);
		
		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9000/olx-login/token/validate")
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andReturn();
		
		String  response=mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("true"),true);
	}
}
