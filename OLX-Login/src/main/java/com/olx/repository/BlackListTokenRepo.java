package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.BlacklistedTokensDocument;

public interface BlackListTokenRepo extends MongoRepository<BlacklistedTokensDocument, Integer>{
	
	BlacklistedTokensDocument findByToken(String authToken);

}
