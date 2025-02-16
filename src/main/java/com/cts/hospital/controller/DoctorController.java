package com.cts.hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospital.model.Doctor;
import com.cts.hospital.model.Patient;
import com.cts.hospital.repository.PatientRepository;
import com.cts.hospital.service.DoctorService;
import com.cts.hospital.service.UserService;

@RestController
@RequestMapping("api/hithro/doctor")
public class DoctorController 
{
	    
	    @Autowired
	    private DoctorService doctorService;
	    
	
	    @GetMapping("/viewAllPatients")
	    public ResponseEntity<List<Object[]>> viewAllPatients(@RequestParam long doctorId) {
	        if (doctorService.isRoleDoctor(doctorId)) {
	            List<Object[]> patients = doctorService.getPatientsWithUserDetails();
	            if (patients.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }
	            return ResponseEntity.ok(patients);
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	    
	    @GetMapping("/viewPatient/{patientId}")
	    public ResponseEntity<Object[]> getPatientById(@PathVariable long patientId, @RequestParam long doctorId) {
	        if (doctorService.isRoleDoctor(doctorId)) {
	            if (doctorService.isRolePatient(patientId)) {
	                Object[] patientDetails = doctorService.getPatientWithUserDetailsById(patientId);
	                if (patientDetails == null) {
	                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	                }
	                return ResponseEntity.ok(patientDetails);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	
}
