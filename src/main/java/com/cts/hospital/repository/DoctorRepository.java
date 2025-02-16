package com.cts.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.hospital.model.Doctor;
import com.cts.hospital.model.Patient;
import com.cts.hospital.model.User;

import jakarta.transaction.Transactional;

public interface DoctorRepository extends JpaRepository<Doctor, Long>
{
	
	Optional<Doctor> findByUser(User user);

	@Query("SELECT u.email, u.name, u.phno, u.dob, u.address, p.medicalHistory, p.currentMedications, p.primaryDoctor " +
	           "FROM User u JOIN u.patient p WHERE u.userId = :id")
	    Object[] findPatientWithUserDetailsById(@Param("id") Long id);
	 
	 @Query("SELECT u.email, u.name, u.phno, u.dob, u.address, p.medicalHistory, p.currentMedications, p.primaryDoctor " +
	           "FROM User u JOIN u.patient p")
	    List<Object[]> findPatientsWithUserDetails();	
	    
	    @Query("SELECT p FROM Doctor p WHERE p.user.id = :userId")
	    Optional<Doctor> findByUserId(@Param("userId") long userId);
	     

}
