package com.bookmymovie.movie.info.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "COUNTRY")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country-generator")
	@SequenceGenerator(name = "country-generator" , sequenceName = "country_generator", initialValue = 1000, allocationSize = 1)	
	private int id;
	@Column(name = "COUNTRY_NAME", length = 200, nullable = false)
	@NotBlank(message = "{country.name.notblank}")
	private String name;
//	@ManyToMany(targetEntity = Movie.class, cascade = CascadeType.ALL, mappedBy = "countries")
//	private List<Movie> movies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public List<Movie> getMovies() {
//		return movies;
//	}
//	public void setMovies(List<Movie> movies) {
//		this.movies = movies;
//	}
		
}

