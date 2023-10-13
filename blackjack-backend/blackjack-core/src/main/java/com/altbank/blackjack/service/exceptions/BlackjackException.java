package com.altbank.blackjack.service.exceptions;

public class BlackjackException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BlackjackException(String msg) {
		super(msg);
	}

	public BlackjackException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
