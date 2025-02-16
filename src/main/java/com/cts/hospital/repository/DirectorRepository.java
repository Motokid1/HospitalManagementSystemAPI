package com.cts.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.hospital.model.Director;
import com.cts.hospital.model.User;

import jakarta.transaction.Transactional;

public interface DirectorRepository extends JpaRepository<Director, Long>{
	
	 Optional<Director> findByUser(User user);
	 
	 @Query("SELECT u.email, u.name, u.phno, u.dob, u.address, p.medicalHistory, p.currentMedications, p.primaryDoctor " +
	           "FROM User u JOIN u.patient p WHERE u.userId = :id")
	    Object[] findPatientWithUserDetailsById(@Param("id") Long id);
	 
	 @Query("SELECT u.email, u.name, u.phno, u.dob, u.address, p.medicalHistory, p.currentMedications, p.primaryDoctor " +
	           "FROM User u JOIN u.patient p")
	    List<Object[]> findPatientsWithUserDetails();
	    
	    
	 @Query("SELECT u.email, u.name, u.phno, u.dob, u.address, d.specialisation " +
		           "FROM User u JOIN u.doctor d WHERE u.userId = :id")
		 Object[] findDoctorWithUserDetailsById(@Param("id") Long id);
		 
	 @Query("SELECT u.email, u.name, u.phno, u.dob, u.address, d.specialisation " +
		           "FROM User u JOIN u.doctor d")
	 	List<Object[]> findDoctorsWithUserDetails();

}
