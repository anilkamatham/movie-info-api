package com.bookmymovie.movie.info.exception;

public class CountryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238340829389712560L;

	public CountryNotFoundException(String msg) {
		super(msg);
	}
}
