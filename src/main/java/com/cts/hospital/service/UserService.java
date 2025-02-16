package com.cts.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.hospital.model.User;

@Service
public interface UserService {
	
	public void addUser(User user);
	public List<User> getAllUsers();
	public Optional<User> getUserById( long id);
	public void deleteUserById(long id);
	public Optional<User> updateUserById(User user, long id);
	public boolean isRoleDoctor(long doctorId);
	public boolean isRolePatient(long patientId);

}
