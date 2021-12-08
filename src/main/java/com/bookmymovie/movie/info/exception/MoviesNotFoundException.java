package com.bookmymovie.movie.info.exception;

public class MoviesNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1072698056148240434L;

	public MoviesNotFoundException(String msg) {
	 super(msg);
	}
}
