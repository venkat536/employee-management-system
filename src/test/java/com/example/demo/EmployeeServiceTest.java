package com.example.demo;



import com.example.demo.COMPANY.Company;
import com.example.demo.COMPANY.CompanyRepository;
import com.example.demo.EMPLOYEE.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldReturnEmployeeById() {

        Employee employee = new Employee();

        employee.setId(1L);
        employee.setName("Ravi");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result =
                employeeService.getEmployeeById(1L);

        assertEquals(
                "Ravi",
                result.getName());
    }
    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {

        when(employeeRepository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(100L)
        );

        verify(employeeRepository)
                .findById(100L);
    }

    @Test
    void shouldSaveEmployee() {

        EmployeeRequestDto dto =
                new EmployeeRequestDto();

        dto.setName("Ravi");
        dto.setDepartment("IT");
        dto.setDepartmentId(101L);
        dto.setCompanyId(1L);

        Company company =
                new Company();

        company.setId(1L);
        company.setCompany_name("TCS");

        Employee savedEmployee =
                new Employee();

        savedEmployee.setId(1L);
        savedEmployee.setName("Ravi");
        savedEmployee.setDepartment("IT");
        savedEmployee.setDepartmentId(101L);
        savedEmployee.setCompany(company);

        when(companyRepository.findById(1L))
                .thenReturn(
                        Optional.of(company));

        when(employeeRepository.save(
                any(Employee.class)))
                .thenReturn(savedEmployee);

        EmployeeResponseDto response =
                employeeService.saveEmployee(dto);

        assertEquals(
                "Ravi",
                response.getName());

        assertEquals(
                "TCS",
                response.getCompanyName());

        verify(companyRepository)
                .findById(1L);

        verify(employeeRepository)
                .save(any(Employee.class));
    }

    @Test
    void shouldThrowExceptionWhenCompanyNotFound() {

        EmployeeRequestDto dto =
                new EmployeeRequestDto();

        dto.setName("Ravi");
        dto.setDepartment("IT");
        dto.setDepartmentId(101L);
        dto.setCompanyId(1L);

        when(companyRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> employeeService.saveEmployee(dto)
                );

        assertEquals(
                "Company Not Found",
                exception.getMessage());

        verify(companyRepository)
                .findById(1L);

        verify(employeeRepository,
                never())
                .save(any(Employee.class));
    }


}
