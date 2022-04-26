package uz.response;

import com.google.gson.annotations.SerializedName;

public class GeocoderResponseMetaData{

	@SerializedName("request")
	private String request;

	@SerializedName("found")
	private String found;

	@SerializedName("Point")
	private Point point;

	@SerializedName("results")
	private String results;

	public String getRequest(){
		return request;
	}

	public String getFound(){
		return found;
	}

	public Point getPoint(){
		return point;
	}

	public String getResults(){
		return results;
	}
}