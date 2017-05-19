package com.iexceed.esoko.domain.utils;

import org.apache.log4j.Logger;

import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class GeoUtils {
	public static Logger log = LoggerUtils.getLogger();

	public static Point getGisValue(String longitude, String latitude) {
		log.debug("Longitude:::" + longitude);
		log.debug("Latitude:::" + latitude);
		GeometryFactory geometryFactory = new GeometryFactory();

		Coordinate coord = new Coordinate(Double.parseDouble(latitude),
				Double.parseDouble(longitude));

		Point point = geometryFactory.createPoint(coord);

		log.debug("Point is:::" + point.toText());
		return point;
	}

	public static String[] getCoordinates(Point point) {
		String[] coordinates = new String[2];
		Coordinate coordinate = point.getCoordinate();
		coordinates[0] = String.valueOf(coordinate.x);
		coordinates[1] = String.valueOf(coordinate.y);
		log.debug("Latitude:::" + coordinates[0]);
		log.debug("Longitude:::" + coordinates[1]);
		return coordinates;
	}
}
