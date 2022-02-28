package com.ssuarez.DisneyApiJava.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssuarez.DisneyApiJava.dto.MovieDTO;
import com.ssuarez.DisneyApiJava.entities.Character;
import com.ssuarez.DisneyApiJava.entities.Movie;
import com.ssuarez.DisneyApiJava.exceptions.ResourceNotFoundException;
import com.ssuarez.DisneyApiJava.repositories.CharacterRepository;
import com.ssuarez.DisneyApiJava.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private CharacterRepository characterRepository;

	@Override
	public List<Object[]> getMovies() {
		return movieRepository.findAllMovies();
	}
	
	@Override
	public MovieDTO findMovieById(Long id) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
		
		return mapperDTO(movie);
	}

	@Override
	public List<Object[]> findByTitle(String title) {
		List<Object[]> listMovies = movieRepository.findByTitle(title);
		
		if (listMovies == null || listMovies.isEmpty()) {
			throw new RuntimeException(String.format("Title movie %s not exixts", title));
		}
		return listMovies;
	}

	@Override
	public List<Object[]> getlAllByOrderAsc(String order) {
		List<Object[]> listMovies = movieRepository.getlAllByOrderASC(order);
		
		if (listMovies == null || listMovies.isEmpty()) {
			throw new RuntimeException("List movies empty");
		}
		return listMovies;
	}
	
	@Override
	public MovieDTO createMovie(MovieDTO movieDTO) {
		Movie movie = mapperEntity(movieDTO);

		Movie newMovie = movieRepository.save(movie);

		MovieDTO movieSave = mapperDTO(newMovie);

		return movieSave;
	}

	@Override
	public MovieDTO updateMovie(MovieDTO movieDTO, Long id) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
		
		movie.setTitle(movieDTO.getTitle());
		movie.setImage(movieDTO.getImage());
		movie.setCreation_date(movieDTO.getCreation_date());
		movie.setRating(movieDTO.getRating());
		movie.getCharacters().addAll(movieDTO.getCharacters()
				.stream()
				.map(character -> {
					Character characterExists = characterRepository.findById(character.getId()).orElseThrow(
							() -> new RuntimeException("Character not exists"));
					characterExists.getMovies().add(movie);
					return characterExists;
				}).collect(Collectors.toList()));
		
		Movie updatedMovie = movieRepository.save(movie);
		
		return mapperDTO(updatedMovie);
	}

	@Override
	public void deleteMovie(Long id) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
		
		movieRepository.delete(movie);
	}

	// Pass Entity to DTO
	private MovieDTO mapperDTO(Movie movie) {
		MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
		return movieDTO;
	}

	// Pass DTO to Entity
	private Movie mapperEntity(MovieDTO movieDTO) {
		Movie movie = modelMapper.map(movieDTO, Movie.class);
		return movie;
	}
	
}
