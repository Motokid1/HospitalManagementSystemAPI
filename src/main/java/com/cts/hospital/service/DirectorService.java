package com.cts.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DirectorService {
	
	public List<Object[]> getPatientsWithUserDetails();
	public Object[] getPatientWithUserDetailsById(Long id);
	public List<Object[]> getDoctorWithUserDetails();
	public Object[] getDoctorWithUserDetailsById(Long id);
	
	public boolean deleteDoctorById(long id);
	public boolean deletePatientById(long id);
	
	public boolean isRoleDoctor(long doctorId);
	public boolean isRolePatient(long patientId);
	public boolean isRoleDirector(long directorId);

}
