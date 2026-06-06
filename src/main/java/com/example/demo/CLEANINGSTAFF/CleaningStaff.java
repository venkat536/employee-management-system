package com.example.demo;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name="cleaning_staff")
public class CleaningStaff {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private  String name;
    private String shiftType;

public CleaningStaff(){

}
public CleaningStaff(Long id,String name,String shiftType){
    this.id=id;
    this.name=name;
    this.shiftType=shiftType;
}
    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


@ManyToOne
  @JoinColumn(name="company_id")
    private Company company;

}
