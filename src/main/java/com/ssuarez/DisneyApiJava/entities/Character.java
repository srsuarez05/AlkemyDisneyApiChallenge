package com.ssuarez.DisneyApiJava.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "characters")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String image;
	@NotEmpty(message="El campo name no puede estar vacio")
	private String name;
	@NotNull(message="El campo age no puede estar vacio")
	private Integer age;
	@NotNull(message="El campo wight no puede estar vacio")
	private Integer weight;
	private String history;
	
	@ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY)
	private Set<Movie> movies = new HashSet<>();
		
	public Character() {
		super();
	}

	public Character(String image, String name, Integer age, Integer weight, String history) {
		super();
		this.image = image;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.history = history;
	}

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
