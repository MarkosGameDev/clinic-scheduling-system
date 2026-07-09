package com.markosdev.clinic.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.markosdev.clinic.dto.CreatePatientDTO;
import com.markosdev.clinic.entity.Patient;
import com.markosdev.clinic.exception.PatientNotFoundException;
import com.markosdev.clinic.repository.PatientRepository;

public class PatientService {

	PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Long createPatient(CreatePatientDTO createPatientDTO) {
		var patient = new Patient(null, 
				createPatientDTO.name(), 
				createPatientDTO.email(), 
				createPatientDTO.phone(), 
				createPatientDTO.address(), 
				createPatientDTO.notes(), 
				Instant.now(), 
				null);
		
		var patientSaved = patientRepository.save(patient);
		return patientSaved.getId();
	}
	
	public Patient getPatientById(Long id) {
		Optional<Patient> patient = patientRepository.findById(id);
		
		if(patient.isEmpty()) {
			throw new PatientNotFoundException("Paciente não encontrado.");
		}
		
		return patient.get();
	}
	
	public List<Patient> listPatient(){
		return patientRepository.findAll();
	}
	
	public void deleteById(Long id) {
		var patientExists = patientRepository.existsById(id);
		if(patientExists) {
			patientRepository.deleteById(id);
		}
		
	}
}
