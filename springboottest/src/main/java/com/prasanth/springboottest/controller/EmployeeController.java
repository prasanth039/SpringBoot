package com.prasanth.springboottest.controller;

import com.prasanth.springboottest.model.Department;
import com.prasanth.springboottest.model.EmpDeptResponse;
import com.prasanth.springboottest.model.Employee;
import com.prasanth.springboottest.request.EmployeeRequest;
import com.prasanth.springboottest.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Value(value = "${app.name}")
    private String appName;

    @GetMapping(value = "/appName")
    public String getAppName() {
        return appName;
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployees(), HttpStatus.OK);
    }

    // http://localhost:8080/employees/1234
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
        return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    //http://localhost:8080/employees?1234
    @DeleteMapping(value = "/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam String id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeRequest employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

//    @PutMapping(value = "/employees/{id}")
//    public ResponseEntity<Employee> putEmployee(@PathVariable String id, @RequestBody Employee employee) {
//        employee.setId(id);
//        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.OK);
//    }

    @GetMapping(value = "/employees/filterByName")
    public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam("name") String name){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/employees/filterByNameContaining")
    public ResponseEntity<List<Employee>> getEmployeeByKeyword(@RequestParam("name") String name){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByKeyword(name), HttpStatus.OK);
    }

    @GetMapping(value = "/employeesByPage")
    public ResponseEntity<List<Employee>> getEmployeePaginated(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesPaginated(pageNumber, pageSize), HttpStatus.OK);
    }

    // Custom query
    @GetMapping(value = "/employeesByNameOrAge")
    public ResponseEntity<List<Employee>> employeesByNameOrAge(@RequestParam("name") String name, @RequestParam("age") int age){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameOrAge(name, age), HttpStatus.OK);
    }

    //http://localhost:8080/employees/deleteByName?Ramesh
    @DeleteMapping(value = "/employees/deleteByName")
    public ResponseEntity<Integer> deleteEmployeeByName(@RequestParam("name") String name) {
        return new ResponseEntity<Integer>(employeeService.deleteEmployeesByNameCustom(name), HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/employees/filterByDepartmentName")
    public ResponseEntity<List<Employee>> getEmployeeByDepartmentName(@RequestParam("name") String name){
        return new ResponseEntity<List<Employee>>(employeeService.findByDepartmentName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/departments")
    public ResponseEntity<List<EmpDeptResponse>> getDepartments() {
        return new ResponseEntity<List<EmpDeptResponse>>(employeeService.getDepartments(), HttpStatus.OK);
    }
}
