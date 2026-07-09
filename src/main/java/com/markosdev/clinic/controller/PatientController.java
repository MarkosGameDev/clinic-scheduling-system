package com.markosdev.clinic.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.markosdev.clinic.dto.CreatePatientDTO;
import com.markosdev.clinic.entity.Patient;
import com.markosdev.clinic.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {

	PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@PostMapping
	public ResponseEntity<Patient> createPatient(@Valid @RequestBody CreatePatientDTO createPatientDTO){
		var patientId = patientService.createPatient(createPatientDTO);
		return ResponseEntity.created(URI.create("/patients/" + patientId.toString())).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
		Patient patient = patientService.getPatientById(id);
		return ResponseEntity.ok(patient);
	}
	
	@GetMapping
	public ResponseEntity<List<Patient>> listPatient(){
		var patients = patientService.listPatient();
		return ResponseEntity.ok(patients);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		patientService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
