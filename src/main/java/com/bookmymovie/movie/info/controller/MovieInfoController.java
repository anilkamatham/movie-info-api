package com.bookmymovie.movie.info.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmymovie.movie.info.model.Movie;
import com.bookmymovie.movie.info.model.MovieModel;
import com.bookmymovie.movie.info.service.MovieInfoService;

@RestController
@RequestMapping("/api/movies")
public class MovieInfoController {

	@Autowired
	private MovieInfoService movieInfoService;
	
	@PostMapping("/add")
	public ResponseEntity<MovieModel> addMovie(@Valid @RequestBody Movie movie){
		
		return new ResponseEntity<MovieModel>(movieInfoService.addMovie(movie), HttpStatus.CREATED);
	}
	
	@GetMapping("/byLang/{lang}")
	public ResponseEntity<List<Movie>> getMoviesByLanguage(@PathVariable("lang") String language){
		return new ResponseEntity<List<Movie>>(movieInfoService.getMoviesByLanguage(language), HttpStatus.OK);
	}
	
	@GetMapping("/byGenre/{genre}")
	public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable("genre") String genre){
		return new ResponseEntity<List<Movie>>(movieInfoService.getMoviesByGenre(genre), HttpStatus.OK);
	}
	
	@GetMapping("/byCity/{city}")
	public ResponseEntity<List<Movie>> getMoviesByCity(@PathVariable("city") String city){
		return new ResponseEntity<List<Movie>>(movieInfoService.getMoviesByCity(city), HttpStatus.OK);
	}
}

