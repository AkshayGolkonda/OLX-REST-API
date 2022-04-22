package com.olx.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;
import com.olx.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/olx-adv")
@CrossOrigin(origins="*")
public class AdvertiseController {
	
	@Autowired
	AdvertiseService advertiseService;
	//8
	@PostMapping(value="/advertise", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Advertise> createNewAdvertise(@RequestBody Advertise advertise, @RequestHeader("Authorization") String authToken) {
	Advertise adv = this.advertiseService.createNewAdvertise(advertise, authToken);
	return new ResponseEntity<Advertise>(adv, HttpStatus.OK);
	}

    //9
    @PutMapping(value = "/advertise/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
    		    MediaType.APPLICATION_XML_VALUE })
    //@ApiOperation(value = "Reads specific stock", notes = "This REST API returns list the stock of given id")
    public ResponseEntity<Advertise> updateAdvertise(@PathVariable("id") int id,@RequestBody Advertise advertise, @RequestHeader("Authorization") String authToken) {
    	return new ResponseEntity<Advertise>(advertiseService.updateAdvertise(id,advertise,authToken),HttpStatus.OK);
    }
    
    // 10 Reads all advertisements posted by logged in user
    @GetMapping(value = "/user/advertise", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    //@ApiOperation(value = "Reads specific stock", notes = "This REST API returns list the stock of given id")
    public ResponseEntity<List<Advertise>> getAllAdvByUser(@RequestHeader("Authorization") String authToken) {
    	return new ResponseEntity<List<Advertise>>(advertiseService.getAllAdvByUser(authToken),HttpStatus.OK);
    }
    
 // 11 Reads specific advertisement posed by logged in user
    @GetMapping(value = "/user/advertise/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    //@ApiOperation(value = "Reads specific stock", notes = "This REST API returns list the stock of given id")
    public ResponseEntity<Advertise> getAdvById(@PathVariable("id") int id,@RequestHeader("Authorization") String authToken) {
	return new ResponseEntity<Advertise>(advertiseService.getAdvById(id,authToken),HttpStatus.OK);
    }
    
    
 // 12 Deletes specific advertisement posted by logged in user
    @DeleteMapping(value = "/user/advertise/{id}")
    //@ApiOperation(value = "Reads specific stock", notes = "This REST API returns list the stock of given id")
    public ResponseEntity<Boolean> deleteAdvById(@PathVariable("id") int id,@RequestHeader("Authorization") String authToken) {
    	return new ResponseEntity<Boolean>(advertiseService.deleteAdvById(id,authToken),HttpStatus.OK);
    }
    
 
    // 13	
    @GetMapping(value="/advertise/search/filtercriteria", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Advertise>> searchAdvertisesByFilterCriteria(@RequestParam(name="searchText", required = false)String searchText,
    @RequestParam(name ="category", required = false)String category, 
    @RequestParam(name="postedBy", required=false)String postedBy,
    @RequestParam(name="dateCondition", required=false)String dateCondition,
    @RequestParam(name="onDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
    @RequestParam(name="fromDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
    @RequestParam(name="toDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
    @RequestParam(name="sortedBy", required=false)String sortedBy, @RequestParam(name = "startIndex", defaultValue="0")int startIndex, @RequestParam(name="records", defaultValue = "10")int records
    ) {
    	List<Advertise> advertises = advertiseService.searchAdvertisesByFilterCriteria(searchText, category, postedBy, dateCondition,
    				onDate, fromDate, toDate, sortedBy, startIndex, records);
    	return new ResponseEntity<List<Advertise>>(advertises,HttpStatus.OK);
    }
    
 // 14 Matches advertisements using the provided
    // 'searchText' within all fields of an advertise.
    @GetMapping(value = "/advertise/search", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    //@ApiOperation(value = "Reads specific stock", notes = "This REST API returns list the stock of given id")
    public ResponseEntity<List<Advertise>> searchAdvByText(@RequestParam("searchText") String searchText) {
    	return new ResponseEntity<List<Advertise>>(advertiseService.SearchAdvByText(searchText),HttpStatus.OK);
    }
    
    
 // 15 Return advertise details
    @GetMapping(value = "/advertise/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    //@ApiOperation(value = "Reads specific stock", notes = "This REST API returns list the stock of given id")
    public ResponseEntity<Advertise> returnAdv(@PathVariable("id") int id,@RequestHeader("Authorization") String authToken) {
	 return new ResponseEntity<Advertise>(advertiseService.returnAdv(id,authToken),HttpStatus.OK);
    }
    
	
}
