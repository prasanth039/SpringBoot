package com.prasanth.springboottest.repository;

import com.prasanth.springboottest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByName(String name);

    // findByName -- Just searches for name -- Select * from table where name = "ramesh"
    // findByNameAndAge -- Searches for and match -- Select * from table where name = "ramesh" and age = 18
    // findByNameContaining -- Searches for contains -- Select * from table where name like "%ramesh%"

    List<Employee> findByNameContaining(String name);

    List<Employee> findByDepartmentName(String name);

    // JPL
    @Query("FROM Employee where department.name = :name")
    List<Employee> getEmployeesByDepartmentName(String name);
}
