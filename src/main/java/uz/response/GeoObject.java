package uz.response;

import com.google.gson.annotations.SerializedName;

public class GeoObject{

	@SerializedName("metaDataProperty")
	private MetaDataProperty metaDataProperty;

	@SerializedName("boundedBy")
	private BoundedBy boundedBy;

	@SerializedName("name")
	private String name;

	@SerializedName("Point")
	private Point point;

	@SerializedName("description")
	private String description;

	public MetaDataProperty getMetaDataProperty(){
		return metaDataProperty;
	}

	public BoundedBy getBoundedBy(){
		return boundedBy;
	}

	public String getName(){
		return name;
	}

	public Point getPoint(){
		return point;
	}

	public String getDescription(){
		return description;
	}
}