package com.bookmymovie.movie.info.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "MOVIE")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","title","description","durationInMinutes","language","releaseDate","genre","countries"})
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie-generator")
	@SequenceGenerator(name = "movie-generator" , sequenceName = "movie_generator", initialValue = 1000, allocationSize = 1)
	private int id;
    @Column(name = "MOVIE_TITLE", length = 50, nullable = false)
    @NotBlank(message = "{movie.title.notblank}")
	private String title;
    @Column(name = "MOVIE_DESCRIPTION", length = 250, nullable = false)
    @NotBlank(message = "{movie.description.notblank}")
	private  String description;
    @Column(name = "MOVIE_DURATION", length = 4 , nullable = false)
    @NotNull(message = "{movie.duration.notnull}")
	private int durationInMinutes;
    @Column(name = "MOVIE_LANGUAGE", length = 50 , nullable = false)
    @NotBlank(message = "{movie.language.notblank}")
	private String language;
    @Column(name = "RELEASE_DATE", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{movie.releasedate.notblank}")
	private Date releaseDate;    
    @ManyToMany(targetEntity = Country.class,  cascade = CascadeType.ALL)
    @JoinTable(
    		name = "t_movie_countries",
    		joinColumns = @JoinColumn(name ="MOVIE_ID", referencedColumnName = "id"), 
    		inverseJoinColumns = @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "id"))
    @Valid
	private List<Country> countries;
    @Column(name = "MOVIE_GENRE", length = 50 , nullable = false)
    @NotBlank(message = "{movie.genre.notblank}")
	private String genre;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDurationInMinutes() {
		return durationInMinutes;
	}
	public void setDurationInMinutes(int duration) {
		this.durationInMinutes = duration;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
			
}
