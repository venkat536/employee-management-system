package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CleaningStaffService {
    private CleaningStaffRepository cleaningStaffRepository;
    public CleaningStaffService(CleaningStaffRepository cleaningStaffRepository){
        this.cleaningStaffRepository=cleaningStaffRepository;
    }
    public List<CleaningStaff> getAllCleaningStaff(){
        return cleaningStaffRepository.findAll();
    }
    public CleaningStaff saveCleaningStaff(CleaningStaff cleaningStaff){
        return cleaningStaffRepository.save(cleaningStaff);
    }
    public CleaningStaff updateCleaningStaff(Long id,CleaningStaff cleaningStaff){
        CleaningStaff existingCleaningStaff=cleaningStaffRepository.findById(id).orElseThrow(() -> new RuntimeException("Staff Not found"));
                existingCleaningStaff.setName(cleaningStaff.getName());
                existingCleaningStaff.setShiftType(cleaningStaff.getShiftType());
                return cleaningStaffRepository.save(existingCleaningStaff);
    }
    public void  deleteCleaningStaff(Long id){
        cleaningStaffRepository.deleteById(id);
    }
    public CleaningStaff getCleaningsStaffById(Long id){
        return cleaningStaffRepository.findById(id).orElseThrow(() -> new RuntimeException("Staff Id Not Found"));
    }


}
