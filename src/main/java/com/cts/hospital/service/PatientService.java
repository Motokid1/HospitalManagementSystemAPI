package com.cts.hospital.service;

import org.springframework.stereotype.Service;

@Service
public interface PatientService {
	
	public Object[] getPatientWithUserDetailsById(Long id);
	public boolean isRolePatient(long patientId);

}
