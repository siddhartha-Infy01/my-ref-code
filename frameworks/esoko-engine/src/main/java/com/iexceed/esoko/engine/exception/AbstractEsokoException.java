package com.iexceed.esoko.engine.exception;

import com.iexceed.esoko.engine.utils.ERROR_CODE;

public abstract class AbstractEsokoException extends RuntimeException implements
		IEsokoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Enum<ERROR_CODE> code = null;
	private String message = null;
	private String type = null;
	private String priority = null;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Enum<ERROR_CODE> getCode() {
		return code;
	}

	public void setCode(Enum<ERROR_CODE> code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
