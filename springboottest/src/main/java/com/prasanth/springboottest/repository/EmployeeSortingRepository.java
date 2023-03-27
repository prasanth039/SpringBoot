package com.prasanth.springboottest.repository;

import com.prasanth.springboottest.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeSortingRepository extends PagingAndSortingRepository<Employee, String> {
    List<Employee> findByNameContaining(String name, Sort sort);

    //SELECT * FROM mydb.tbl_employee where name = "TEST" OR age = 20
    @Query("FROM Employee where name = :name or age = :age")
    List<Employee> getEmployeeByNameOrAge(String name, int age);


    //DELETE FROM mydb.tbl_employee where name = "Ramesh"
    @Query("DELETE FROM Employee where name = :name")
    @Modifying
    @Transactional
    Integer customDelete(String name);
}
