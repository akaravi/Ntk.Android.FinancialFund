package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BaseFundEntity<TKey> {
    @SerializedName("Id")
    public TKey id;
    @SerializedName("createdDate")
    public Date createdDate;
    @SerializedName("createdBy")
    public long createdBy;
    @SerializedName("updatedDate")
    public Date updatedDate;
    @SerializedName("updatedBy")
    public long updatedBy;
    @SerializedName("recordStatus")
    public int recordStatus;
    @SerializedName("recordStatusText")
    public String recordStatusText;

}
