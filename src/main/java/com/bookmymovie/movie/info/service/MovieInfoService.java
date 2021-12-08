package com.bookmymovie.movie.info.service;

import java.util.List;

import com.bookmymovie.movie.info.model.Movie;
import com.bookmymovie.movie.info.model.MovieModel;

public interface MovieInfoService {

	public List<Movie> getMoviesByLanguage(String language);
	public List<Movie> getMoviesByGenre(String genre);
	public List<Movie> getMoviesByCity(String city);
	public MovieModel addMovie(Movie movie);
}
