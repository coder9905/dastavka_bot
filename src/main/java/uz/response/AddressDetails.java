package uz.response;

import com.google.gson.annotations.SerializedName;

public class AddressDetails{

	@SerializedName("Country")
	private Country country;

	public Country getCountry(){
		return country;
	}
}