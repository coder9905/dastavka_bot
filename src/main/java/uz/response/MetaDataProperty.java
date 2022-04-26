package uz.response;

import com.google.gson.annotations.SerializedName;

public class MetaDataProperty{

	@SerializedName("GeocoderResponseMetaData")
	private GeocoderResponseMetaData geocoderResponseMetaData;

	@SerializedName("GeocoderMetaData")
	private GeocoderMetaData geocoderMetaData;

	public GeocoderResponseMetaData getGeocoderResponseMetaData(){
		return geocoderResponseMetaData;
	}

	public GeocoderMetaData getGeocoderMetaData(){
		return geocoderMetaData;
	}
}