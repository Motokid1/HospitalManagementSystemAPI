package com.cts.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospital.service.PatientService;

@RestController
@RequestMapping("api/hithro/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/viewPatient/{patientId}")
    public ResponseEntity<Object[]> getPatientById(@PathVariable long patientId) {
        
            if (patientService.isRolePatient(patientId)) 
            {
                Object[] patientDetails = patientService.getPatientWithUserDetailsById(patientId);
                if (patientDetails == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
                return ResponseEntity.ok(patientDetails);
            } 
            else {
            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        
        
    }

}
