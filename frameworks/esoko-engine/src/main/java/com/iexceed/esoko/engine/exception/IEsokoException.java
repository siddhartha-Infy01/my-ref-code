package com.iexceed.esoko.engine.exception;

import com.iexceed.esoko.engine.utils.ERROR_CODE;

// TODO: Auto-generated Javadoc
/**
 * The Interface IEsokoException.
 */
public interface IEsokoException {

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public Enum<ERROR_CODE> getCode();

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage();

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType();

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public String getPriority();

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(Enum<ERROR_CODE> code);

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message);

	/**
	 * Sets the type.
	 *
	 * @param type the type
	 * @return the string
	 */
	public void setType(String type);

	/**
	 * Sets the priority.
	 *
	 * @param priority the priority
	 * @return the string
	 */
	public void setPriority(String priority);
}
