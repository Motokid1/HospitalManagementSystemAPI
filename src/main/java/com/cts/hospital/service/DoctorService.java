package com.cts.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.hospital.model.Patient;

@Service
public interface DoctorService {
	
	public boolean isRoleDoctor(long doctorId);
	public boolean isRolePatient(long patientId);
	public List<Object[]> getPatientsWithUserDetails();
	public Object[] getPatientWithUserDetailsById(Long id);

}
