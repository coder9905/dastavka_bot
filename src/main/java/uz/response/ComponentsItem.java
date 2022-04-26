package uz.response;

import com.google.gson.annotations.SerializedName;

public class ComponentsItem{

	@SerializedName("kind")
	private String kind;

	@SerializedName("name")
	private String name;

	public String getKind(){
		return kind;
	}

	public String getName(){
		return name;
	}
}