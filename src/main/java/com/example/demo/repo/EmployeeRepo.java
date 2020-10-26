package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.model;

@Repository
public interface EmployeeRepo extends MongoRepository<model,Long> {
	
	model findByfirstname(String fname);
	

}
