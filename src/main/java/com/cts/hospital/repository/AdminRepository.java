package com.cts.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.hospital.model.Admin;
import com.cts.hospital.model.User;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	 Optional<Admin> findByUser(User user);

}
