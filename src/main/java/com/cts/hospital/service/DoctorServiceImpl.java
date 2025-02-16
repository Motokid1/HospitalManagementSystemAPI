package com.cts.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospital.config.Role;
import com.cts.hospital.model.Doctor;
import com.cts.hospital.model.Patient;
import com.cts.hospital.model.User;
import com.cts.hospital.repository.DoctorRepository;
import com.cts.hospital.repository.PatientRepository;
import com.cts.hospital.repository.UserRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;


	public List<Object[]> getPatientsWithUserDetails() {
        return doctorRepository.findPatientsWithUserDetails();
    }
	
	@Override
	public boolean isRolePatient(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> user.getRole() == Role.PATIENT).orElse(false);
    }
	
	@Override
	public boolean isRoleDoctor(long doctorId) {
		 Optional<User> userOptional = userRepository.findById(doctorId);
	     return userOptional.map(user -> user.getRole() == Role.DOCTOR).orElse(false);
	}

	public Object[] getPatientWithUserDetailsById(Long id) {
        return doctorRepository.findPatientWithUserDetailsById(id);
    }

}
