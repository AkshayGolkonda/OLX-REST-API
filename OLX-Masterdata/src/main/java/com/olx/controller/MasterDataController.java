package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.service.MasterDataService;

@RestController
@RequestMapping("/olx-masterdata")
@CrossOrigin(origins="*")
public class MasterDataController {
	
	@Autowired
	MasterDataService masterDataService;
	
	@GetMapping(value="/advertise/category",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Category> getAllCategories(){
		return masterDataService.getAllCategories();
	}
	
	@GetMapping(value="/advertise/status",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Status> getAllStatus(){
		return masterDataService.getAllStatus();
	}
	
	@GetMapping(value="/advertise/category/{id}",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String getCategoryById(@PathVariable("id")int id){
		return masterDataService.getCategoryById(id);
	}
	
	@GetMapping(value="/advertise/status/{id}",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String getStatusById(@PathVariable("id")int id){
		return masterDataService.getStatusById(id);
	}
	
	/*
	 * @PostMapping(value="/advertise/category", consumes=
	 * {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	 * produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	 * public Category postCategory(@RequestBody Category category){ return
	 * masterDataService.postCategory(category); }
	 * 
	 * @PostMapping(value="/advertise/status", consumes=
	 * {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	 * produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	 * public Status postStatus(@RequestBody Status status){ return
	 * masterDataService.postStatus(); }
	 */
	
}
