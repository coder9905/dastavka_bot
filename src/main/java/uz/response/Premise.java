package uz.response;

import com.google.gson.annotations.SerializedName;

public class Premise{

	@SerializedName("PremiseName")
	private String premiseName;

	public String getPremiseName(){
		return premiseName;
	}
}