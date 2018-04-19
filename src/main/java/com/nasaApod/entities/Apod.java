package com.nasaApod.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Apod represents the Astronomy Picture object Of the Day
 * @author Olezt
 *
 */
public class Apod {

	@Getter @Setter private String service_version;
	@Getter @Setter private String media_type;
	@Getter @Setter private String explanation;
	@Getter @Setter private String title;
	@Getter @Setter private String url;
	@Getter @Setter private String copyright;
	@Getter @Setter private String date;
  
	public Apod() {}

}
