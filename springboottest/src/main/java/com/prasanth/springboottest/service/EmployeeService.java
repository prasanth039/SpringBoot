package com.prasanth.springboottest.service;

import com.prasanth.springboottest.model.Department;
import com.prasanth.springboottest.model.EmpDeptResponse;
import com.prasanth.springboottest.model.Employee;
import com.prasanth.springboottest.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee saveEmployee(EmployeeRequest employee);
    Employee getEmployee(String id);
    void deleteEmployee(String id);
    List<Employee> getEmployeeByName(String name);
    List<Employee> getEmployeeByKeyword(String name);
    List<Employee> getEmployeesPaginated(int pageNumber, int pageSize);
    List<Employee> getEmployeesByNameOrAge(String name, int age);
    Integer deleteEmployeesByNameCustom(String name);

    // Join
    List<Employee> findByDepartmentName(String name);

    List<EmpDeptResponse> getDepartments();
}
