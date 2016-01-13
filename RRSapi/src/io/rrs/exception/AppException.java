package io.rrs.exception;

public class AppException extends Exception {

	private static final long serialVersionUID = 6019581695501618649L;

	public AppException(String msg){
		super(msg);
	}
	
	public AppException(String msg, Throwable cause){
		super(msg, cause);
	}
}
