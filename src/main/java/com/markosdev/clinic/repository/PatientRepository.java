package com.markosdev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.markosdev.clinic.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
