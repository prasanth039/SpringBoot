package com.prasanth.springboottest.service;

import com.prasanth.springboottest.model.Department;
import com.prasanth.springboottest.model.EmpDeptResponse;
import com.prasanth.springboottest.model.Employee;
import com.prasanth.springboottest.repository.DepartmentRepository;
import com.prasanth.springboottest.repository.EmployeeRepository;
import com.prasanth.springboottest.repository.EmployeeSortingRepository;
import com.prasanth.springboottest.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeSortingRepository employeeSortingRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        Department department = departmentRepository.save(employeeRequest.getDepartment());
        employeeRequest.getEmployee().setDepartment(department);
        return employeeRepository.save(employeeRequest.getEmployee());
    }

    @Override
    public Employee getEmployee(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.isPresent() ? employee.get() : null;
    }

    @Override
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeeByKeyword(String name) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return employeeSortingRepository.findByNameContaining(name, sort);
    }

    @Override
    public List<Employee> getEmployeesPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "name");
        return employeeSortingRepository.findAll(pageable).stream().toList();
    }

    @Override
    public List<Employee> getEmployeesByNameOrAge(String name, int age) {
        return employeeSortingRepository.getEmployeeByNameOrAge(name, age);
    }

    @Override
    public Integer deleteEmployeesByNameCustom(String name) {
        return employeeSortingRepository.customDelete(name);
    }

    @Override
    public List<Employee> findByDepartmentName(String name) {
        return employeeRepository.getEmployeesByDepartmentName(name);
    }

    @Override
    public List<EmpDeptResponse> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<EmpDeptResponse> list = new ArrayList<>();
        departments.forEach(department -> {
            EmpDeptResponse response = new EmpDeptResponse();
            response.setDeptId(department.getId());
            response.setDeptName(department.getName());
            list.add(response);
        });
        return list;
    }

}
