package com.bookmymovie.movie.info.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookmymovie.movie.info.exception.CountryNotFoundException;
import com.bookmymovie.movie.info.exception.MoviesNotFoundException;
import com.bookmymovie.movie.info.model.Country;
import com.bookmymovie.movie.info.model.CountryModel;
import com.bookmymovie.movie.info.model.Movie;
import com.bookmymovie.movie.info.model.MovieModel;
import com.bookmymovie.movie.info.model.Show;
import com.bookmymovie.movie.info.model.Theatre;
import com.bookmymovie.movie.info.repository.MovieInfoDao;
import com.bookmymovie.movie.info.service.MovieInfoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

	@Autowired
	private MovieInfoDao movieInfoDao;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Movie> getMoviesByLanguage(String language) {
		List<Movie> movies = movieInfoDao.findByLanguage(language);
		if (movies != null && movies.size() > 0)
			return movies;
		else
			throw new MoviesNotFoundException("No Movies found by language " + language);
	}

	@Override
	public List<Movie> getMoviesByGenre(String genre) {
		List<Movie> movies = movieInfoDao.findByGenre(genre);
		if (movies != null && movies.size() > 0)
			return movies;
		else
			throw new MoviesNotFoundException("No Movies found by Genre " + genre);
	}

	@Override	
	public List<Movie> getMoviesByCity(String city) {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8082/api/theater/byCity/" + city,
				String.class);
		ObjectMapper mapper = new ObjectMapper();
		List<Theatre> theatres = null;
		try {
			if (response.getStatusCode().is2xxSuccessful()) {
				theatres = mapper.readValue(response.getBody(), new TypeReference<List<Theatre>>() {
				});

			}

		} catch (JsonParseException je) {

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (theatres != null && theatres.size() > 0) {
			// List<Integer> theatreIds = theatres.stream().map(theatre ->
			// theatre.getId()).collect(Collectors.toList());

			List<Movie> movies = getMoviesByTheatre(theatres);
			return movies;
		}

		return null;
	}

	private List<Movie> getMoviesByTheatre(List<Theatre> theatres) {
		List<Integer> allMovieIds = new ArrayList<>();
		theatres.stream().forEach(theatre -> {
			ResponseEntity<String> response = restTemplate
					.getForEntity("http://localhost:8083/api/show/getMovies/" + theatre.getId(), String.class);
			ObjectMapper mapper = new ObjectMapper();
			List<Integer> movieIds = new ArrayList<>();
			try {
				if (response.getStatusCode().is2xxSuccessful()) {
					movieIds = mapper.readValue(response.getBody(), new TypeReference<List<Integer>>() {
					});
					allMovieIds.addAll(movieIds);
				}

			} catch (JsonParseException je) {

			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		return movieInfoDao.findAllById(allMovieIds);
	}

	@Override
	public MovieModel addMovie(Movie movie) {
		if (movie.getCountries() == null || movie.getCountries().size() < 0)
			throw new CountryNotFoundException("Movie should have at least one Country");
		Movie savedMovie = movieInfoDao.save(movie);
		MovieModel movieModel = new MovieModel();
		movieModel.setId(savedMovie.getId());
		movieModel.setTitle(savedMovie.getTitle());
		;
		movieModel.setDescription(savedMovie.getDescription());
		movieModel.setDurationInMinutes(savedMovie.getDurationInMinutes());
		movieModel.setLanguage(savedMovie.getLanguage());
		movieModel.setReleaseDate(savedMovie.getReleaseDate());
		movieModel.setGenre(savedMovie.getGenre());
		movieModel.setCountries(getCountries(movie));
		return movieModel;
	}

	private List<CountryModel> getCountries(Movie movie) {

		List<CountryModel> countriesList = movie.getCountries().stream().map(country -> {
			CountryModel countryModel = new CountryModel();
			countryModel.setId(country.getId());
			countryModel.setName(country.getName());
			return countryModel;
		}).collect(Collectors.toList());
		return countriesList;
	}

}
