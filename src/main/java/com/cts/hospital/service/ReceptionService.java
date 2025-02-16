package com.cts.hospital.service;

import org.springframework.stereotype.Service;

import com.cts.hospital.model.User;

@Service
public interface ReceptionService {
	
	public void addPatient(User user);
	public boolean isRoleReceptionist(long receptionistId);

}
