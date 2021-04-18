package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

public class ClientTokenModel {
    @SerializedName("guidKey")
    public String guidKey;
    @SerializedName("orgin")
    public String orgin;
    @SerializedName("packageName")
    public String packageName;
    @SerializedName("expireDate")
    public String expireDate;
    @SerializedName("Token")
    public String token;
    @SerializedName("user")
    public UserToken user;
    @SerializedName("app")
    public AppToken app;
    @SerializedName("device")
    public DeviceToken device;
    @SerializedName("smsIsChecked")
    public boolean smsIsChecked;
}
