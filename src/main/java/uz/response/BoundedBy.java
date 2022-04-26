package uz.response;

import com.google.gson.annotations.SerializedName;

public class BoundedBy{

	@SerializedName("Envelope")
	private Envelope envelope;

	public Envelope getEnvelope(){
		return envelope;
	}
}