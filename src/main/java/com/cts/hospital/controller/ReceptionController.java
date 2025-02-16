package com.cts.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospital.model.User;
import com.cts.hospital.service.ReceptionService;

@RestController
@RequestMapping("api/hithro/reception")
public class ReceptionController {
	
	@Autowired
	private ReceptionService receptionService;
	
	@PostMapping("/addPatient")
    public ResponseEntity<String> addPatient(@RequestParam long receptionistId, @RequestBody User user) {
        if (receptionService.isRoleReceptionist(receptionistId)) {
            receptionService.addPatient(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient added successfully.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access.");
    }

}
