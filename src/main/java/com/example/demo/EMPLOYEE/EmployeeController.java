package com.example.demo;



import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
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
public EmployeeResponseDto saveEmployee( @Valid @RequestBody EmployeeRequestDto dto){
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
}
