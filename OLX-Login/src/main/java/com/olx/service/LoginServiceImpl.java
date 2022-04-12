package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.User;
import com.olx.entity.UserEntity;
import com.olx.repository.UserRepo;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UserRepo userRepo;
	
	//@Autowired
	ModelMapper modelMapper=new ModelMapper();

	@Override
	public String authenticate(User user) {
		// TODO Auto-generated method stub
		return "Akshay";
	}

	@Override
	public boolean logout(String authToken) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public User addUser(User user) {
		UserEntity userEntity=convertDTOIntoEntity(user);
		userRepo.save(userEntity);
		return convertEntityIntoDTO(userEntity);
	}

	@Override
	public User getUser(String authtoken) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepo.findByFirstName(authtoken); 
		return convertEntityIntoDTO(userEntity);
	}

	@Override
	public boolean validate(String authToken) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	private UserEntity convertDTOIntoEntity(User user) {
		TypeMap<User, UserEntity> tMap = modelMapper.typeMap(User.class, UserEntity.class);
		/*
		 * tMap.addMappings(mapper->{ mapper.map(); });
		 */
		UserEntity userEntity=modelMapper.map(user,UserEntity.class);
		return userEntity;
	}
	
	private User convertEntityIntoDTO(UserEntity userEntity) {
		TypeMap<UserEntity, User> tMap=modelMapper.typeMap(UserEntity.class, User.class);
		/*
		 * tMap.addMappings(mapper->{ mapper.map(); });
		 */
		User user=modelMapper.map(userEntity, User.class);
		return user;
	}

	
}
