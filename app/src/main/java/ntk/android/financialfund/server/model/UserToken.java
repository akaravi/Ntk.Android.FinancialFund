package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

public class UserToken {
    @SerializedName("guidKey")
    public String guidKey;
    @SerializedName("orgin")
    public String orgin;
    @SerializedName("packageName")
    public String packageName;
    @SerializedName("expireDate")
    public String expireDate;
    @SerializedName("mobileNumber")
    public String mobileNumber;
    @SerializedName("nationalCode")
    public String nationalCode;
    @SerializedName("username")
    public String username;
    @SerializedName("userId")
    public Integer userId;
    @SerializedName("os")
    public String os;
    @SerializedName("uniqueDeviceId")
    public String uniqueDeviceId;
    @SerializedName("appName")
    public String appName;
    @SerializedName("appVersion")
    public String appVersion;
    @SerializedName("smsValue")
    public String smsValue;
    @SerializedName("captchaKey")
    public String captchaKey;
    @SerializedName("captchaValue")
    public String captchaValue;
    @SerializedName("branchGuidKey")
    public String branchGuidKey;
}
