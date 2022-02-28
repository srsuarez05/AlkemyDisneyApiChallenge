package com.ssuarez.DisneyApiJava.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ssuarez.DisneyApiJava.entities.Movie;

public class CharacterDTO {
	
	private Long id;
	private String image;
	
	@NotEmpty(message="El campo name no puede estar vacio")
	private String name;
	
	@NotNull(message="El campo age no puede estar vacio")
	private Integer age;
	
	@NotNull(message="El campo wight no puede estar vacio")
	private Integer weight;
	private String history;	
	private Set<Movie> movies;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	} 

}
