package uz.response;

import com.google.gson.annotations.SerializedName;

public class Point{

	@SerializedName("pos")
	private String pos;

	public String getPos(){
		return pos;
	}
}