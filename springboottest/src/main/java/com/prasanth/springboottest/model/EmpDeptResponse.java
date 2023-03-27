package com.prasanth.springboottest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmpDeptResponse {
    private String deptName;
    private String deptId;
    private String empName;
}
