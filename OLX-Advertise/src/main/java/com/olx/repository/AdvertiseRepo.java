package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepo extends JpaRepository<AdvertiseEntity, Integer> {
	
	List<AdvertiseEntity> findByUsername(String username);

}
