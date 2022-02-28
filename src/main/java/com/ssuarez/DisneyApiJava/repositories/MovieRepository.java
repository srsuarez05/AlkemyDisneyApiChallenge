package com.ssuarez.DisneyApiJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssuarez.DisneyApiJava.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("select image, title, creation_date from Movie")
	public List<Object[]> findAllMovies();
	
	public List<Object[]> findByTitle(String title);
	
	@Query("select m FROM Movie m ORDER BY creation_date ASC")
	public List<Object[]> getlAllByOrderASC(String order);
	
	@Query("select m FROM Movie m ORDER BY creation_date DESC")
	public List<Object[]> getlAllByOrderDESC(String order);
}
