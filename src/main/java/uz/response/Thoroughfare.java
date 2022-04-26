package uz.response;

import com.google.gson.annotations.SerializedName;

public class Thoroughfare{

	@SerializedName("ThoroughfareName")
	private String thoroughfareName;

	public String getThoroughfareName(){
		return thoroughfareName;
	}
}