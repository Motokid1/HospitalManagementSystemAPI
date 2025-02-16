package com.cts.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospital.config.Role;
import com.cts.hospital.model.Admin;
import com.cts.hospital.model.Director;
import com.cts.hospital.model.Doctor;
import com.cts.hospital.model.Patient;
import com.cts.hospital.model.Receptionist;
import com.cts.hospital.model.User;
import com.cts.hospital.repository.AdminRepository;
import com.cts.hospital.repository.DirectorRepository;
import com.cts.hospital.repository.DoctorRepository;
import com.cts.hospital.repository.PatientRepository;
import com.cts.hospital.repository.ReceptionRepository;
import com.cts.hospital.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService 
{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	DirectorRepository directorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	ReceptionRepository receptionRepository;

	@Override
	public void addUser(User user) {
		
		userRepository.save(user);
		
		switch (user.getRole()) {
	    case ADMIN:
	        if (user.getAdmin() != null) { // Ensure Admin exists
	            Admin admin = user.getAdmin();
	            admin.setUser(user);
	            adminRepository.save(admin);
	        }
	        break;

	    case DOCTOR:
	        if (user.getDoctor() != null) { // Ensure Doctor exists
	            Doctor doctor = user.getDoctor();
	            doctor.setUser(user);
	            doctorRepository.save(doctor);
	        }
	        break;

	    case DIRECTOR:
	        if (user.getDirector() != null) { // Ensure Director exists
	            Director director = user.getDirector();
	            director.setUser(user);
	            directorRepository.save(director);
	        }
	        break;

	    case PATIENT:
	        if (user.getPatient() != null) { // Ensure Patient exists
	            Patient patient = user.getPatient();
	            patient.setUser(user);
	            patientRepository.save(patient);
	        }
	        break;

	    case RECEPTIONIST:
	        if (user.getReceptionist() != null) { // Ensure Receptionist exists
	            Receptionist receptionist = user.getReceptionist();
	            receptionist.setUser(user);
	            receptionRepository.save(receptionist);
	        }
	        break;
	}

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteUserById(long id) {
		 userRepository.deleteById(id);
	}
	
	@Override
	public Optional<User> updateUserById( User user,long id) 
	{
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setEmail(user.getEmail());
            existingUser.setName(user.getName());            
            userRepository.save(existingUser);

            switch (user.getRole()) {
            case DOCTOR:
                Optional<Doctor> doctorOptional = doctorRepository.findByUser(existingUser);
                if (doctorOptional.isPresent()) {
                    Doctor doctor = doctorOptional.get();
                    if (user.getDoctor() != null && user.getDoctor().getSpecialisation() != null) {
                        doctor.setSpecialisation(user.getDoctor().getSpecialisation());
                    }
                    doctorRepository.save(doctor);
                }
                break;

            case ADMIN:
                Optional<Admin> adminOptional = adminRepository.findByUser(existingUser);
                if (adminOptional.isPresent()) {
                    Admin admin = adminOptional.get();
                    adminRepository.save(admin);
                }
                break;

            case DIRECTOR:
                Optional<Director> directorOptional = directorRepository.findByUser(existingUser);
                if (directorOptional.isPresent()) {
                    Director director = directorOptional.get();
                    // Update director-specific fields if needed
                    directorRepository.save(director);
                }
                break;

            case PATIENT:
                Optional<Patient> patientOptional = patientRepository.findByUser(existingUser);
                if (patientOptional.isPresent()) {
                    Patient patient = patientOptional.get();
                    if (user.getPatient() != null) {
                        if (user.getPatient().getMedicalHistory() != null) {
                            patient.setMedicalHistory(user.getPatient().getMedicalHistory());
                        }
                        if (user.getPatient().getCurrentMedications() != null) {
                            patient.setCurrentMedications(user.getPatient().getCurrentMedications());
                        }
                        if (user.getPatient().getPrimaryDoctor() != null) {
                            patient.setPrimaryDoctor(user.getPatient().getPrimaryDoctor());
                        }
                    }
                    patientRepository.save(patient);
                }
                break;

            case RECEPTIONIST:
                Optional<Receptionist> receptionistOptional = receptionRepository.findByUser(existingUser);
                if (receptionistOptional.isPresent()) {
                    Receptionist receptionist = receptionistOptional.get();
                    // Update receptionist-specific fields if needed
                    receptionRepository.save(receptionist);
                }
                break;
        }

            return Optional.of(existingUser);
        }
        return Optional.empty();
    }
		
	
	public boolean isRoleDoctor(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> user.getRole() == Role.DOCTOR).orElse(false);
    }
	
	public boolean isRolePatient(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> user.getRole() == Role.PATIENT).orElse(false);
    }
	
	public boolean isRoleDirector(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> user.getRole() == Role.DIRECTOR).orElse(false);
    }
		
	
	public boolean isRoleAdmin(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> user.getRole() == Role.ADMIN).orElse(false);
    }
}