package com.ecom.productservicejune24.inheritancetypes.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_students")
public class Student extends User {
    private String batch;
}