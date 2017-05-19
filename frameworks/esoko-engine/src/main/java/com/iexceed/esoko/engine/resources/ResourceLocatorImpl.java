package com.iexceed.esoko.engine.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public final class ResourceLocatorImpl implements IResourceLocator {
	private Logger log = LoggerUtils.getLogger();

	public InputStream getResourceFromClasspath(String fileName)
			throws IOException {
		InputStream inputSteam = getClass().getClassLoader()
				.getResourceAsStream(fileName);
		return inputSteam;
	}

	public File getResourceFromPath(String resurcePath, String resurceName)
			throws IOException {
		Resource resource = new FileSystemResource(resurcePath + resurceName);
		return resource.getFile();
	}

	public InputStream getResourceFromURL(URL url) throws IOException {
		Resource resource = new UrlResource(url);

		return resource.getInputStream();
	}

	public File getFileFromClasspath(String fileName) throws IOException {
		Resource resource = new ClassPathResource(fileName);
		return resource.getFile();
	}

	public File getFileFromPath(String resurcePath, String resurceName)
			throws IOException {
		Resource resource = new FileSystemResource(resurcePath + resurceName);
		return resource.getFile();
	}

	public File getFileFromURL(URL url) throws IOException {
		Resource resource = new UrlResource(url);
		return resource.getFile();
	}

	public Properties getEsokoProperties() {
		Properties esokoProperties = new Properties();
		try {
			esokoProperties.load(getResourceFromClasspath("esoko.properties"));
		} catch (IOException e) {
			log.info("Error reading esoko.properties file");
		}
		return esokoProperties;
	}
}
