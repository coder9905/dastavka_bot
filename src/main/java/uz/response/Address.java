package uz.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Address{

	@SerializedName("Components")
	private List<ComponentsItem> components;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("formatted")
	private String formatted;

	public List<ComponentsItem> getComponents(){
		return components;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getFormatted(){
		return formatted;
	}
}