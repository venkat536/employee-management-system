package com.example.demo.CLEANINGSTAFF;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cleaning_staff")
@SecurityRequirement(name = "Bearer Authentication")
public class CleaningStaffController {
    private CleaningStaffService cleaningStaffService;
    public CleaningStaffController(CleaningStaffService cleaningStaffService){
        this.cleaningStaffService=cleaningStaffService;
    }
    @GetMapping
    public List<CleaningStaff> getAllCleaningStaff(){
        return cleaningStaffService.getAllCleaningStaff();
    }
    @PostMapping
    public CleaningStaff saveCleaningStaff(@RequestBody CleaningStaff cleaningStaff){
        return cleaningStaffService.saveCleaningStaff(cleaningStaff);
    }
    @PutMapping("/{id}")
    public CleaningStaff updateCleaningStaff(@PathVariable Long id,CleaningStaff cleaningStaff){
        return cleaningStaffService.updateCleaningStaff(id,cleaningStaff);
    }
    @DeleteMapping("/{id}")
    public String deleteCleaningStaff(@PathVariable Long id){
        cleaningStaffService.deleteCleaningStaff(id);
        return "Cleaning Staff deleted";

    }
    @GetMapping("/{id}")
    public CleaningStaff cleaningStaff(@PathVariable Long id){
        return cleaningStaffService.getCleaningsStaffById(id);
    }
}
