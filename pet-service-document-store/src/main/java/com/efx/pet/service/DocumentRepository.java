package com.efx.pet.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DocumentRepository extends MongoRepository<Document, String> {
}