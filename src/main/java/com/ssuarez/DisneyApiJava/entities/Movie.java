package com.ssuarez.DisneyApiJava.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String image;
	
	@NotEmpty(message="El campo title no puede estar vacio")
	private String title;
	
	@NotNull(message="El creation_date name no puede estar vacio")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date creation_date;
	
	@Min(1)
	@Max(5)
	private Integer rating;
	
	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movies_characters", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),	
								inverseJoinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"))
	private Set<Character> characters = new HashSet<>();
	
	public Movie() {
		super();
	}

	public Movie(String image, String title, Date creation_date, Integer rating) {
		super();
		this.image = image;
		this.title = title;
		this.creation_date = creation_date;
		this.rating = rating;
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

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}
	
}
