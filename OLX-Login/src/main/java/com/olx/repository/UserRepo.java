package com.olx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	List<UserEntity> findByUserName(String username);
	Optional<UserEntity> findByFirstName(String authToken);
}
