package com.cts.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospital.repository.DoctorRepository;
import com.cts.hospital.service.DirectorService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("api/hithro/director")
public class DirectorController {
	
	@Autowired
	private DirectorService directorService;
	
	 @GetMapping("/viewAllPatients")
	    public ResponseEntity<List<Object[]>> viewAllPatients(@RequestParam long directorId) {
	        if (directorService.isRoleDirector(directorId)) {
	            List<Object[]> patients = directorService.getPatientsWithUserDetails();
	            if (patients.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }
	            return ResponseEntity.ok(patients);
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	    
	    @GetMapping("/viewPatient/{patientId}")
	    public ResponseEntity<Object[]> getPatientById(@PathVariable long patientId, @RequestParam long directorId) {
	        if (directorService.isRoleDirector(directorId)) {
	            if (directorService.isRolePatient(patientId)) {
	                Object[] patientDetails = directorService.getPatientWithUserDetailsById(patientId);
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
	    	    
	    @GetMapping("/viewAllDoctors")
	    public ResponseEntity<List<Object[]>> viewAllDoctors(@RequestParam long directorId) {
	        if (directorService.isRoleDirector(directorId)) {
	            List<Object[]> doctors = directorService.getDoctorWithUserDetails();
	            if (doctors.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }
	            return ResponseEntity.ok(doctors);
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	    
	    @GetMapping("/viewDoctor/{doctorId}")
	    public ResponseEntity<Object[]> getDoctortById(@PathVariable long doctorId, @RequestParam long directorId) {
	        if (directorService.isRoleDirector(directorId)) {
	            if (directorService.isRoleDoctor(doctorId)) {
	                Object[] doctorDetails = directorService.getDoctorWithUserDetailsById(doctorId);
	                if (doctorDetails == null) {
	                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	                }
	                return ResponseEntity.ok(doctorDetails);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	    
	    @DeleteMapping("/deleteDoctor/{userId}")
	    public ResponseEntity<String> deleteDoctorByUserId(@PathVariable long userId, @RequestParam long directorId) {
	        if (directorService.isRoleDirector(directorId)) {
	            boolean deleted = directorService.deleteDoctorById(userId);
	            if (deleted) {
	                return ResponseEntity.ok("Doctor  deleted successfully.");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found.");
	            }
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access.");
	    }
	    
	    
	    @DeleteMapping("/deletePatient/{userId}")
	    public ResponseEntity<String> deletePatientByUserId(@PathVariable long userId, @RequestParam long directorId) {
	        if (directorService.isRoleDirector(directorId)) {
	            boolean deleted = directorService.deletePatientById(userId);
	            if (deleted) {
	                return ResponseEntity.ok("Patient and User deleted successfully.");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
	            }
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access.");
	    }
}

