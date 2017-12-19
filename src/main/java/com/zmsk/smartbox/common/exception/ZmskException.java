package com.zmsk.smartbox.controller.common.exception;

public abstract class ZmskException extends RuntimeException {

	private static final long serialVersionUID = 6851274131509494649L;

	public ZmskException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZmskException(String message) {
		super(message);
	}

	public ZmskException(Throwable cause) {
		super(cause);
	}

	public ZmskException() {
		super();
	}

	public abstract int getCode();

}
