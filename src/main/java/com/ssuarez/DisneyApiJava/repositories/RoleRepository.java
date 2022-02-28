package com.ssuarez.DisneyApiJava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssuarez.DisneyApiJava.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public Optional<Role> findByName(String name);
	
}
