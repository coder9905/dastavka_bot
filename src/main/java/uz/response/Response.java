package uz.response;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("response")
	private Response response;

	@SerializedName("GeoObjectCollection")
	private GeoObjectCollection geoObjectCollection;

	public Response getResponse(){
		return response;
	}

	public GeoObjectCollection getGeoObjectCollection(){
		return geoObjectCollection;
	}
}