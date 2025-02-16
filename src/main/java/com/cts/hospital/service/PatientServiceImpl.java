package com.cts.hospital.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospital.config.Role;
import com.cts.hospital.model.User;
import com.cts.hospital.repository.DirectorRepository;
import com.cts.hospital.repository.PatientRepository;
import com.cts.hospital.repository.UserRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Object[] getPatientWithUserDetailsById(Long id) {
        return patientRepository.findPatientWithUserDetailsById(id);
    }
	
	@Override
	public boolean isRolePatient(long patientId) {
        Optional<User> userOptional = userRepository.findById(patientId);
        return userOptional.map(user -> user.getRole() == Role.PATIENT).orElse(false);
    }

}
