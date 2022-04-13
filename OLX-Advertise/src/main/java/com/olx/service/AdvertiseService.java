package com.olx.service;

import java.time.LocalDate;
import java.util.List;
import com.olx.dto.Advertise;


public interface AdvertiseService {
	public Advertise postAdvertise(Advertise adv);
	 public Advertise updateAdvertise(Advertise adv);
	 public List<Advertise> getAllAdvByUser();
	 public List<Advertise> getAdvByUser(String uname);
	 public boolean deleteAdvByUserId();
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
	 public Advertise SearchAdvByText(String searchText);
	 public Advertise returnAdv(int id);
}
