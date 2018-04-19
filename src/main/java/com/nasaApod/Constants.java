package com.nasaApod;

public final class Constants {

	private Constants() {}

	public static final String APOD_API = "https://api.nasa.gov/planetary/apod";
	public static final String APOD_API_KEY_PARAM_NAME = "api_key";
	public static final String APOD_API_KEY_PARAM_VALUE = "DEMO_KEY";
	public static final String APOD_API_DATE_PARAM_NAME = "date";
	public static final String APOD_API_START_DATE_PARAM_NAME = "start_date";
	public static final String APOD_API_END_DATE_PARAM_NAME = "end_date";
	public static final String APOD_API_ACCEPTABLE_DATE_FORMAT = "yyyy-MM-dd";

	public static final String APOD_DOWNLOAD_REQUEST_BEAN_PDF_CELL_TITLE = "Title:";
	public static final String APOD_DOWNLOAD_REQUEST_BEAN_PDF_CELL_COPYRIGHT = "Copyright:";
	public static final String APOD_DOWNLOAD_REQUEST_BEAN_PDF_CELL_EXPLANATION = "Explanation:";
	public static final String APOD_DOWNLOAD_REQUEST_BEAN_PDF_CELL_IMAGE_ERROR = "Error image occured.";
	
	public static final String APOD_VIEW_BEAN_DATE_PARAM_NAME = "date";

}
