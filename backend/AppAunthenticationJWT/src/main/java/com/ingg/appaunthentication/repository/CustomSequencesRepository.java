package com.ingg.appaunthentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ingg.appaunthentication.domain.CustomSequences;

public interface CustomSequencesRepository extends MongoRepository<CustomSequences, String> {

}
