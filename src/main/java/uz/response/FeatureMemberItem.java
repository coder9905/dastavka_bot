package uz.response;

import com.google.gson.annotations.SerializedName;

public class FeatureMemberItem{

	@SerializedName("GeoObject")
	private GeoObject geoObject;

	public GeoObject getGeoObject(){
		return geoObject;
	}
}