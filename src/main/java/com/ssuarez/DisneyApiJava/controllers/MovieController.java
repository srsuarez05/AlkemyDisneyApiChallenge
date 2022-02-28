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

import com.ssuarez.DisneyApiJava.dto.MovieDTO;
import com.ssuarez.DisneyApiJava.services.MovieService;

@RestController
@RequestMapping("movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public List<Object[]> listMovies(){
		return movieService.getMovies();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> getMovieById(@PathVariable(value= "id") Long id){
		return ResponseEntity.ok(movieService.findMovieById(id));	
	}

	@GetMapping(params="title")
    public List<Object[]> findByTitle(@RequestParam("title") String title){
        return movieService.findByTitle(title);
    }
    
    @GetMapping(params="order")
    public List<Object[]> getlAllByOrderASC(@RequestParam("order") String order){
        return movieService.getlAllByOrderAsc(order);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveMovie(@Valid @RequestBody MovieDTO movieDTO){
    	Map<String, Object> response = new HashMap<>();
    	
    	MovieDTO movie  = movieService.createMovie(movieDTO);
    	
    	response.put("status", "CREATED");
    	response.put("message", "Movie successfully saved");
    	response.put("movie", movie);
    	
    	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@Valid @RequestBody MovieDTO movieDTO, @PathVariable(value= "id") Long id){
    	Map<String, Object> response = new HashMap<>();
    	
    	MovieDTO movie  = movieService.updateMovie(movieDTO, id);
    	
    	response.put("status", "OK");
    	response.put("message", "Movie successfully modified");
    	response.put("movie", movie);
    	
    	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable(value = "id") Long id){
    	MovieDTO movie = movieService.findMovieById(id);
		String namePreviousImage = movie.getImage();
		
		if (namePreviousImage != null && namePreviousImage.length() > 0) {
			Path routePreviousFile = Paths.get("uploads").resolve(namePreviousImage).toAbsolutePath();
			File filePreviousImage = routePreviousFile.toFile();
			
			if (filePreviousImage.exists() && filePreviousImage.canRead()) {
				filePreviousImage.delete();					
			}
		}
		
		movieService.deleteMovie(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Movie delete success!!");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("fileImage") MultipartFile fileImage, @RequestParam(value = "id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		MovieDTO movie = movieService.findMovieById(id);
		
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
			
			String namePreviousImage = movie.getImage();
			
			if (namePreviousImage != null && namePreviousImage.length() > 0) {
				Path routePreviousFile = Paths.get("uploads").resolve(namePreviousImage).toAbsolutePath();
				File filePreviousImage = routePreviousFile.toFile();
				
				if (filePreviousImage.exists() && filePreviousImage.canRead()) {
					filePreviousImage.delete();					
				}
			}
			
			movie.setImage(nameFile);			
			movieService.updateMovie(movie, id);
			
			response.put("status", "Ok");
			response.put("message", "Image successfully uploaded: "+routeFile);
			response.put("movie", movie);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
