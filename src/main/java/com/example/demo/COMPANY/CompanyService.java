package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }
    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }
    public Company saveCompany(Company company){
        return companyRepository.save(company);
    }
    public Company updateCompany(Long id ,Company company){
        Company existingCompany=companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company Id Not Found"));
        existingCompany.setCompany_name(company.getCompany_name());
        existingCompany.setLocation(company.getLocation());
        return companyRepository.save(existingCompany);
    }
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
        public Company getCompanyById(Long id){
            return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("COMPANY ID NOT FOUND"));

    }
}