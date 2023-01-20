package com.jdbc.test.empinfo;
import java.lang.*;
public class EmployeeInfo {
    public int empid;
    public String empname;
    public double empsalary;
    public String designation;

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpsalary(double empsalary) {
        this.empsalary = empsalary;
    }

    public double getEmpsalary() {
        return empsalary;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
}
