package com.kaplan.assignment.errors;

public class AssignmentNotFoundException extends  RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1662404635706939732L;

	public AssignmentNotFoundException(String message) {
		super(message);
	}

}
