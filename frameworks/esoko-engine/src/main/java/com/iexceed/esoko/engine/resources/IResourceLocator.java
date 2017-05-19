/*
 * 
 */
package com.iexceed.esoko.engine.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

// TODO: Auto-generated Javadoc
/**
 * The Interface IResourceLocator.
 */
public interface IResourceLocator {

	/**
	 * Gets the resource from context.
	 * 
	 * @param fileName
	 *            the file name
	 * @return the resource from context
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public InputStream getResourceFromClasspath(String fileName)
			throws IOException;
	

	/**
	 * Gets the file from classpath.
	 *
	 * @param fileName the file name
	 * @return the file from classpath
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public File getFileFromClasspath(String fileName) throws IOException;

	/**
	 * Gets the resource from path.
	 *
	 * @param resurcePath the resurce path
	 * @param resurceName the resurce name
	 * @return the resource from path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public File getResourceFromPath(String resurcePath, String resurceName)
			throws IOException;

	/**
	 * Gets the resource from path.
	 * 
	 * @param resurcePath
	 *            the resurce path
	 * @param resurceName
	 *            the resurce name
	 * @return the resource from path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public File getFileFromPath(String resurcePath, String resurceName)
			throws IOException;

	/**
	 * Gets the resource from url.
	 * 
	 * @param url
	 *            the url
	 * @return the resource from url
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public InputStream getResourceFromURL(URL url) throws IOException;

	/**
	 * Gets the file from url.
	 *
	 * @param url the url
	 * @return the file from url
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public File getFileFromURL(URL url) throws IOException;

}
