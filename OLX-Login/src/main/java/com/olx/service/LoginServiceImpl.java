package com.olx.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.User;
import com.olx.entity.UserEntity;
import com.olx.exception.InvalidAuthTokenException;
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
		Optional<UserEntity> userEntity = userRepo.findByFirstName(authtoken); 
		if(userEntity.isPresent()) {
			return convertEntityIntoDTO(userEntity.get());
		}
		throw new InvalidAuthTokenException();
	}

	@Override
	public boolean validate(String authToken) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	private UserEntity convertDTOIntoEntity(User user) {
		UserEntity userEntity=modelMapper.map(user,UserEntity.class);
		return userEntity;
	}
	
	private User convertEntityIntoDTO(UserEntity userEntity) {
		User user=modelMapper.map(userEntity, User.class);
		return user;
	}

	
}
