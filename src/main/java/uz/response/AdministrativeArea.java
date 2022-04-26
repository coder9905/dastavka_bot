package uz.response;

import com.google.gson.annotations.SerializedName;

public class AdministrativeArea{

	@SerializedName("AdministrativeAreaName")
	private String administrativeAreaName;

	@SerializedName("Locality")
	private Locality locality;

	public String getAdministrativeAreaName(){
		return administrativeAreaName;
	}

	public Locality getLocality(){
		return locality;
	}
}