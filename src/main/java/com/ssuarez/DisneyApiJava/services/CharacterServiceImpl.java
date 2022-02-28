package com.ssuarez.DisneyApiJava.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssuarez.DisneyApiJava.entities.Character;
import com.ssuarez.DisneyApiJava.entities.Movie;
import com.ssuarez.DisneyApiJava.dto.CharacterDTO;
import com.ssuarez.DisneyApiJava.repositories.CharacterRepository;
import com.ssuarez.DisneyApiJava.repositories.MovieRepository;
import com.ssuarez.DisneyApiJava.exceptions.ResourceNotFoundException;

@Service
public class CharacterServiceImpl implements CharacterService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CharacterRepository characterRepository;
	
	@Autowired
	private MovieRepository movieRepository;
		
	@Override
	public List<Object[]> getCharacters(){
		return characterRepository.findAllCharacters();
	}	

	@Override
	public CharacterDTO createCharacter(CharacterDTO characterDTO) {
		Character character = mapperEntity(characterDTO);
				
		Character newCharacter = characterRepository.save(character);
		
		CharacterDTO characterSave = mapperDTO(newCharacter);
		
		return characterSave;
	}

	@Override
	public CharacterDTO findCharacterById(Long id) {
		Character character = characterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
		
		return mapperDTO(character);
	}
	
	@Override
	public List<Object[]> findByName(String name) {
		List<Object[]> listCharacters = characterRepository.findByName(name);
		
		if (listCharacters == null || listCharacters.isEmpty()) {
			//throw new ResourceNotFoundException("Character", "name", name));
			throw new RuntimeException(String.format("Name %s not exixts", name));
		}
		return listCharacters;
    }
	
	@Override
    public List<Object[]> findByAge(Integer age) {
    	List<Object[]> listCharacters = characterRepository.findByAge(age);
		
		if (listCharacters == null || listCharacters.isEmpty()) {
			throw new RuntimeException(String.format("Age %d not exixts", age));
		}
		return listCharacters;
    }

	@Override
	public CharacterDTO updateCharacter(CharacterDTO characterDTO, Long id) {
		Character character = characterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
		
		character.setName(characterDTO.getName());
		character.setAge(characterDTO.getAge());
		character.setWeight(characterDTO.getWeight());
		character.setHistory(characterDTO.getHistory());
		character.setImage(characterDTO.getImage());
		character.getMovies().addAll(characterDTO.getMovies()
			.stream()
			.map(movie -> {
				Movie movieExists = movieRepository.findById(movie.getId()).orElseThrow(
						() -> new RuntimeException("Movie not exists"));
				movieExists.getCharacters().add(character);
				return movieExists;
			}).collect(Collectors.toList()));
		
		Character updatedCharacter = characterRepository.save(character);
		
		return mapperDTO(updatedCharacter);
	}

	@Override
	public void deleteCharacter(Long id) {
		Character character = characterRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
		
		characterRepository.delete(character);		
	}
	
	// Pass Entity to DTO
	private CharacterDTO mapperDTO(Character character) {
		CharacterDTO characterDTO = modelMapper.map(character, CharacterDTO.class);
		return characterDTO;
	}
	
	// Pass DTO to Entity
	private Character mapperEntity(CharacterDTO characterDTO) {
		Character character = modelMapper.map(characterDTO, Character.class);
		return character;
	}

}
