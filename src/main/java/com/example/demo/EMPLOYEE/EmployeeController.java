package com.example.demo.EMPLOYEE;



import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/employees")
@SecurityRequirement(name = "Bearer Authentication")
public class EmployeeController {
private final EmployeeService employeeService;
public EmployeeController(EmployeeService employeeService){
    this.employeeService=employeeService;
}
@GetMapping
public List<Employee> getEmployees(){
    return employeeService.getAllEmployee();
}
@PostMapping
public EmployeeResponseDto saveEmployee(@Valid @RequestBody EmployeeRequestDto dto){
return employeeService.saveEmployee(dto);
}
@GetMapping("/{id}")
public Employee employeeById( @PathVariable Long id){
    return employeeService.getEmployeeById(id);
}
@PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id , @Valid @RequestBody Employee employee){
return employeeService.updateEmployee( id,employee);
}
@DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
employeeService.deleteEmployee(id);
return "Employee Deleted Successfully";
}
    @GetMapping("/page")
    public Page<Employee> getEmployees(
            Pageable pageable) {

        return employeeService
                .getEmployees(pageable);
    }


}
