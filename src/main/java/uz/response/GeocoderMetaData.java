package uz.response;

import com.google.gson.annotations.SerializedName;

public class GeocoderMetaData{

	@SerializedName("Address")
	private Address address;

	@SerializedName("AddressDetails")
	private AddressDetails addressDetails;

	@SerializedName("kind")
	private String kind;

	@SerializedName("precision")
	private String precision;

	@SerializedName("text")
	private String text;

	public Address getAddress(){
		return address;
	}

	public AddressDetails getAddressDetails(){
		return addressDetails;
	}

	public String getKind(){
		return kind;
	}

	public String getPrecision(){
		return precision;
	}

	public String getText(){
		return text;
	}
}