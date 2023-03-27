package com.prasanth.springboottest.request;

import com.prasanth.springboottest.model.Department;
import com.prasanth.springboottest.model.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private Employee employee;
    private Department department;
}
