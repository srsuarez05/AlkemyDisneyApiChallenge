package com.ssuarez.DisneyApiJava.CharacterTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssuarez.DisneyApiJava.repositories.CharacterRepository;

@DataJpaTest
public class CharacterRepositoryTest {

	@Autowired
	private CharacterRepository characterRepository;
	
	@Test
	void findByNameTest() {
		List<Object[]> characters = characterRepository.findByName("Hércules");		
		assertEquals(1, characters.size());
	}
	
	@Test
	void findByNameNotExistsTest() {
		List<Object[]> characters = characterRepository.findByName("Boo");
		assertTrue(characters.isEmpty());
	}
	
	@Test
	void findByAgeTest() {
		List<Object[]> characters = characterRepository.findByAge(28);		
		assertEquals(1, characters.size());
	}
	
	@Test
	void findByAgeNotExistsTest() {
		List<Object[]> characters = characterRepository.findByAge(30);
		assertTrue(characters.isEmpty());
	}
	
}
