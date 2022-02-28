package com.ssuarez.DisneyApiJava.services;

import java.util.List;

import com.ssuarez.DisneyApiJava.dto.CharacterDTO;

public interface CharacterService {

	public List<Object[]> getCharacters();
	
	public List<Object[]> findByName(String name);
	
	public List<Object[]> findByAge(Integer age);
	
	public CharacterDTO findCharacterById(Long id);
	
	public CharacterDTO createCharacter(CharacterDTO characterDTO);	
	
	public CharacterDTO updateCharacter(CharacterDTO characterDTO, Long id);
	
	public void deleteCharacter(Long id);

}
