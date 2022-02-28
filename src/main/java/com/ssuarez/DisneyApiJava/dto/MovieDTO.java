package com.ssuarez.DisneyApiJava.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MovieDTO {

	private Long id;
	private String image;
	@NotEmpty(message="El campo title no puede estar vacio")
	private String title;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@NotNull(message="El creation_date name no puede estar vacio")
	private Date creation_date;
	
	@Min(1)
	@Max(5)
	private Integer rating;
	private Set<CharacterDTOMovies> characters;
	
	public MovieDTO() {
		super();
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Set<CharacterDTOMovies> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<CharacterDTOMovies> characters) {
		this.characters = characters;
	}

}
