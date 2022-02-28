package com.ssuarez.DisneyApiJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssuarez.DisneyApiJava.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long>{

}
