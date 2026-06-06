package com.example.demo;

import com.example.demo.COMPANY.Company;
import com.example.demo.COMPANY.CompanyRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class EmployeeService  {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public EmployeeResponseDto saveEmployee(EmployeeRequestDto dto) {

        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setDepartmentId(dto.getDepartmentId());

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() ->
                        new RuntimeException("Company Not Found"));

        employee.setCompany(company);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeResponseDto response = new EmployeeResponseDto();

        response.setId(savedEmployee.getId());
        response.setName(savedEmployee.getName());
        response.setDepartment(savedEmployee.getDepartment());
        response.setDepartmentId(savedEmployee.getDepartmentId());
        response.setCompanyName(
                savedEmployee.getCompany().getCompany_name());

        return response;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee Not Found with id " + id));
    }



        public Employee updateEmployee(Long id,Employee employee){
    Employee existingEmployee = employeeRepository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found with id " + id));
            existingEmployee.setName(employee.getName());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setDepartmentId(employee.getDepartmentId());
            return employeeRepository.save(existingEmployee);

        }
        public void  deleteEmployee(Long id){
    employeeRepository.deleteById(id);
        }
}
