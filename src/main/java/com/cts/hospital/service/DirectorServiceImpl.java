package com.cts.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hospital.config.Role;
import com.cts.hospital.model.Doctor;
import com.cts.hospital.model.Patient;
import com.cts.hospital.model.User;
import com.cts.hospital.repository.DirectorRepository;
import com.cts.hospital.repository.DoctorRepository;
import com.cts.hospital.repository.PatientRepository;
import com.cts.hospital.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class DirectorServiceImpl implements DirectorService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private DirectorRepository directorRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	

	public List<Object[]> getPatientsWithUserDetails() {
        return directorRepository.findPatientsWithUserDetails();
    }
	

	public Object[] getPatientWithUserDetailsById(Long id) {
        return directorRepository.findPatientWithUserDetailsById(id);
    }

	@Override
	public List<Object[]> getDoctorWithUserDetails() {
		return directorRepository.findDoctorsWithUserDetails();
	}

	@Override
	public Object[] getDoctorWithUserDetailsById(Long id) {
		return directorRepository.findDoctorWithUserDetailsById(id);
	}

	@Override
	public boolean isRoleDoctor(long doctorId) {
		 Optional<User> userOptional = userRepository.findById(doctorId);
	     return userOptional.map(user -> user.getRole() == Role.DOCTOR).orElse(false);
	}

	@Override
	public boolean isRolePatient(long patientId) {
        Optional<User> userOptional = userRepository.findById(patientId);
        return userOptional.map(user -> user.getRole() == Role.PATIENT).orElse(false);
    }

	@Override
	public boolean isRoleDirector(long directorId) {
		 Optional<User> userOptional = userRepository.findById(directorId);
	     return userOptional.map(user -> user.getRole() == Role.DIRECTOR).orElse(false);
	}

	public boolean deletePatientById(long userId) 
	{
        System.out.println("Finding patient with User ID: " + userId);

        Optional<Patient> patientOptional = patientRepository.findByUserId(userId);

        if (patientOptional.isPresent()) {
            System.out.println("Passed One Check - Patient Found!");
            Patient patient = patientOptional.get();

            patientRepository.delete(patient); // Delete patient entry
            System.out.println("Passed Second Check - Patient Deleted!");

            userRepository.deleteById(userId); // Delete user entry
            System.out.println("Passed Third Check - User Deleted!");

            return true;
        }

        System.out.println("Patient Not Found!");
        return false;
    }
	
	@Override
	public boolean deleteDoctorById(long userId) 
	{
        System.out.println("Finding doctor with User ID: " + userId);

        Optional<Doctor> doctorOptional = doctorRepository.findByUserId(userId);

        if (doctorOptional.isPresent()) {
            System.out.println("Passed One Check - Patient Found!");
            Doctor doctor = doctorOptional.get();

            doctorRepository.delete(doctor); // Delete patient entry
            System.out.println("Passed Second Check - Patient Deleted!");

            userRepository.deleteById(userId); // Delete user entry
            System.out.println("Passed Third Check - User Deleted!");

            return true;
        }

        System.out.println("Doctor Not Found!");
        return false;
    }
}
