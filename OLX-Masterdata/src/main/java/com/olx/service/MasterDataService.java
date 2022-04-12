package com.olx.service;

import java.util.List;
import com.olx.dto.Category;
import com.olx.dto.Status;

public interface MasterDataService {

	//public MasterData addNewAdvertise(MasterData data,String authToken);
	//public MasterData updateAdvertise(int id,MasterData data,String authToken);
	//public List<MasterData> getAllMasterData(String authToken);
	//public MasterData getMasterDataOfUserById(int id,String authToken);
	//public boolean deleteMasterDataById(int id,String authToken);
	//public MasterData getMasterDataById(int id,String authToken);

	public List<Category> getAllCategories();
	public List<Status> getAllStatus();
	//public Category postCategory(Category category);
	//public Status postStatus();
}
