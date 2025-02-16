package com.cts.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.hospital.model.Admin;
import com.cts.hospital.model.Patient;
import com.cts.hospital.model.User;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.user.id = :userId")
    Optional<Patient> findByUserId(@Param("userId") long userId);
    
    Optional<Patient> findByUser(User user);
    
    @Query("SELECT u.email, u.name, u.phno, u.dob, u.address, p.medicalHistory, p.currentMedications, p.primaryDoctor " +
	           "FROM User u JOIN u.patient p WHERE u.userId = :id")
	    Object[] findPatientWithUserDetailsById(@Param("id") Long id);
}

