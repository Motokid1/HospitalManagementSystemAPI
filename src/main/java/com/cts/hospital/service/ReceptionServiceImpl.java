package com.cts.hospital.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospital.config.Role;
import com.cts.hospital.model.Patient;
import com.cts.hospital.model.User;
import com.cts.hospital.repository.PatientRepository;
import com.cts.hospital.repository.UserRepository;

@Service
public class ReceptionServiceImpl implements ReceptionService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void addPatient(User user) {
		
		userRepository.save(user);
		if (user.getPatient() != null) { // Ensure Patient exists
            Patient patient = user.getPatient();
            patient.setUser(user);
            patientRepository.save(patient);
        }
	}
	
	public boolean isRoleReceptionist(long userId) {
	    Optional<User> userOptional = userRepository.findById(userId);
	    return userOptional.map(user -> user.getRole() == Role.RECEPTIONIST).orElse(false);
	}

}
