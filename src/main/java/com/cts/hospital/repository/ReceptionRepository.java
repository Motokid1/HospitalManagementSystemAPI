package com.cts.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.hospital.model.Receptionist;
import com.cts.hospital.model.User;

public interface ReceptionRepository extends JpaRepository<Receptionist, Long> {
	
	 Optional<Receptionist> findByUser(User user);

}
