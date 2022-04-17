package com.olx.service;

import java.time.LocalDate;
import java.util.List;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;


public interface AdvertiseService {
	public Advertise createNewAdvertise(Advertise adv, String authToken);
	 public Advertise updateAdvertise(int id, Advertise adv, String authToken);
	 public List<Advertise> getAllAdvByUser(String authToken);
	 public Advertise getAdvById(int id, String authToken);
	 public boolean deleteAdvById(int id, String authToken);
	 public List<Advertise> searchAdvertisesByFilterCriteria(String searchText,
			    String category,
			    String postedBy,
			    String dateCondition,
			    LocalDate onDate,
			    LocalDate fromDate,
			    LocalDate toDate,
			    String sortedBy,
			    int startIndex,
			    int records);
	 public List<Advertise> SearchAdvByText(String searchText);
	 public Advertise returnAdv(int id, String authToken);
}
