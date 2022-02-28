package com.ssuarez.DisneyApiJava.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssuarez.DisneyApiJava.dto.CharacterDTO;
import com.ssuarez.DisneyApiJava.services.CharacterService;

@RestController
@RequestMapping("characters")
public class CharacterController {

	@Autowired
	private CharacterService characterService;
	
	@GetMapping	
	public List<Object[]> listCharacters(){
		return characterService.getCharacters();
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable(value= "id") Long id){
		return ResponseEntity.ok(characterService.findCharacterById(id));
	}
	
	@GetMapping(params="name")
    public List<Object[]> findByName(@RequestParam("name") String name){
        return characterService.findByName(name);
    }
    
    @GetMapping(params="age")
    public List<Object[]> findByAge(@RequestParam("age") Integer age){
        return characterService.findByAge(age);
    }
	
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> saveCharacter(@Valid @RequestBody CharacterDTO characterDTO){
		Map<String, Object> response = new HashMap<>();
		
		CharacterDTO character = characterService.createCharacter(characterDTO);
		
		response.put("status", "CREATED");
		response.put("message", "Character successfully saved");
		response.put("character", character);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCharacter(@Valid @RequestBody CharacterDTO characterDTO, @PathVariable(value = "id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		CharacterDTO character = characterService.updateCharacter(characterDTO, id);
		
		response.put("status", "OK");
		response.put("message", "Character successfully modified");
		response.put("character", character);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCharacter(@PathVariable(value = "id") Long id){
		CharacterDTO character = characterService.findCharacterById(id);
		String namePreviousImage = character.getImage();
		
		if (namePreviousImage != null && namePreviousImage.length() > 0) {
			Path routePreviousFile = Paths.get("uploads").resolve(namePreviousImage).toAbsolutePath();
			File filePreviousImage = routePreviousFile.toFile();
			
			if (filePreviousImage.exists() && filePreviousImage.canRead()) {
				filePreviousImage.delete();					
			}
		}
		
		characterService.deleteCharacter(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Character delete success!!");
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("fileImage") MultipartFile fileImage, @RequestParam(value = "id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		CharacterDTO character = characterService.findCharacterById(id);
		
		if (!fileImage.isEmpty()) {
			String nameFile = UUID.randomUUID().toString() +"_"+ fileImage.getOriginalFilename().replace(" ", "");
			Path routeFile = Paths.get("uploads").resolve(nameFile).toAbsolutePath();
			
			try {
				Files.copy(fileImage.getInputStream(), routeFile);
			} catch (IOException e) {
				response.put("message", "Error uploading image: "+routeFile);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String namePreviousImage = character.getImage();
			
			if (namePreviousImage != null && namePreviousImage.length() > 0) {
				Path routePreviousFile = Paths.get("uploads").resolve(namePreviousImage).toAbsolutePath();
				File filePreviousImage = routePreviousFile.toFile();
				
				if (filePreviousImage.exists() && filePreviousImage.canRead()) {
					filePreviousImage.delete();					
				}
			}
			
			character.setImage(nameFile);			
			characterService.updateCharacter(character, id);
			
			response.put("status", "Ok");
			response.put("message", "Image successfully uploaded: "+routeFile);
			response.put("character", character);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
