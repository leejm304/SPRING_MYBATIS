package com.my.exception;

public class AddException extends RuntimeException{ // -> UncheckedException -> 자동 롤백

	public AddException() {
		super();
	}

	public AddException(String message) {
		super(message);

	}
}
