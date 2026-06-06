package com.example.demo;

import com.example.demo.COMPANY.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="employees")
public class Employee {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NotBlank(message = "Employee Name Cannot be Empty")
    private String name;
@NotBlank(message = "Department Cannot be empty")
    private String department;
@NotNull(message="Department id is required")
    private long departmentId;

    public Employee(){

    }
    public Employee(Long id,String name,String department,Long departmentId){
this.id=id;
this.name=name;
this.department=department;
this.departmentId=departmentId;
    }

public long getId(){
        return id;
}
    public String getName(){
        return name;

    }
    public String getDepartment(){
        return department;
    }
    public long getDepartmentId(){
        return departmentId;
    }

    public void setId(Long id){
        this.id=id;
    }
public void setName(String name){
        this.name=name;
}
public void setDepartment(String department){
        this .department=department;
}
public void setDepartmentId(Long  departmentId){
        this.departmentId=departmentId;
}

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
@ManyToOne
    @JoinColumn(name="company_id")
    private Company company;
}
