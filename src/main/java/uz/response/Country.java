package uz.response;

import com.google.gson.annotations.SerializedName;

public class Country{

	@SerializedName("CountryName")
	private String countryName;

	@SerializedName("AddressLine")
	private String addressLine;

	@SerializedName("CountryNameCode")
	private String countryNameCode;

	@SerializedName("AdministrativeArea")
	private AdministrativeArea administrativeArea;

	public String getCountryName(){
		return countryName;
	}

	public String getAddressLine(){
		return addressLine;
	}

	public String getCountryNameCode(){
		return countryNameCode;
	}

	public AdministrativeArea getAdministrativeArea(){
		return administrativeArea;
	}
}