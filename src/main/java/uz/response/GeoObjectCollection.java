package uz.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GeoObjectCollection{

	@SerializedName("metaDataProperty")
	private MetaDataProperty metaDataProperty;

	@SerializedName("featureMember")
	private List<FeatureMemberItem> featureMember;

	public MetaDataProperty getMetaDataProperty(){
		return metaDataProperty;
	}

	public List<FeatureMemberItem> getFeatureMember(){
		return featureMember;
	}
}