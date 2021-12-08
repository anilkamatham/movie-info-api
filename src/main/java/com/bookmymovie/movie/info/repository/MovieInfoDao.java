package com.bookmymovie.movie.info.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmymovie.movie.info.model.Movie;


public interface MovieInfoDao extends JpaRepository<Movie, Integer> {
	
	public List<Movie> findByLanguage(String language);
	public List<Movie> findByGenre(String Genre);
}
