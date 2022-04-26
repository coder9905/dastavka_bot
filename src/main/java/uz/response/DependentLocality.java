package uz.response;

import com.google.gson.annotations.SerializedName;

public class DependentLocality{

	@SerializedName("DependentLocalityName")
	private String dependentLocalityName;

	@SerializedName("Premise")
	private Premise premise;

	public String getDependentLocalityName(){
		return dependentLocalityName;
	}

	public Premise getPremise(){
		return premise;
	}
}