package uz.response;

import com.google.gson.annotations.SerializedName;

public class Locality{

	@SerializedName("LocalityName")
	private String localityName;

	@SerializedName("DependentLocality")
	private DependentLocality dependentLocality;

	@SerializedName("Thoroughfare")
	private Thoroughfare thoroughfare;

	public String getLocalityName(){
		return localityName;
	}

	public DependentLocality getDependentLocality(){
		return dependentLocality;
	}

	public Thoroughfare getThoroughfare(){
		return thoroughfare;
	}
}