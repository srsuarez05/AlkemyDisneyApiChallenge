package com.ssuarez.DisneyApiJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssuarez.DisneyApiJava.entities.Character;

public interface CharacterRepository extends JpaRepository<Character, Long>{
	
	public List<Object[]> findByName(String name);
	
	public List<Object[]> findByAge(Integer age);
	
	//@Query(value = "select image, name from characters;", nativeQuery = true)
	@Query("select image, name from Character")
	public List<Object[]> findAllCharacters();
}
