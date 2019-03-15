package com.developer.architecture.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.developer.architecture.app.domain.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {
	
    public List<Employee> findByDisplayName(String employeeName);
    
    @Query("{'children.0': {$exists: false}}")
    public List<Employee> findAllPossibleParent();
    
    public List<Employee> findAll();
    
    public Employee findByGuid(String guid);
    
}