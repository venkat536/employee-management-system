package com.example.demo;


import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/COMPANY")
public class CompanyController {
    private CompanyService companyService;
    public CompanyController(CompanyService companyService){
        this.companyService=companyService;
    }
    @GetMapping
    public List<Company> getCompany(){
        return companyService.getAllCompany();
    }
    @PostMapping
    public Company saveCompany(@RequestBody Company company){
        return companyService.saveCompany(company);
    }
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id ,Company company){
        return companyService.updateCompany(id, company);
    }
    @DeleteMapping("/{id}")
    public String deleteCompany(Long id){
        companyService.deleteCompany(id);
        return "Deleted Company";
    }
    @GetMapping("/{id}")
        public Company company (@PathVariable Long id){
            return companyService.getCompanyById(id);
        }

}
