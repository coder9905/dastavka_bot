package uz.response;

import com.google.gson.annotations.SerializedName;

public class Envelope{

	@SerializedName("lowerCorner")
	private String lowerCorner;

	@SerializedName("upperCorner")
	private String upperCorner;

	public String getLowerCorner(){
		return lowerCorner;
	}

	public String getUpperCorner(){
		return upperCorner;
	}
}