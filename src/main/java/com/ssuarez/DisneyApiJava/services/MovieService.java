package com.ssuarez.DisneyApiJava.services;

import java.util.List;

import com.ssuarez.DisneyApiJava.dto.MovieDTO;

public interface MovieService {

	public List<Object[]> getMovies();
	
	public List<Object[]> findByTitle(String title);
	
	public List<Object[]> getlAllByOrderAsc(String order);
	
	public MovieDTO findMovieById(Long id);
		
	public MovieDTO createMovie(MovieDTO movieDTO);
	
	public MovieDTO updateMovie(MovieDTO movieDTO, Long id);
	
	public void deleteMovie(Long id);
	 
}
