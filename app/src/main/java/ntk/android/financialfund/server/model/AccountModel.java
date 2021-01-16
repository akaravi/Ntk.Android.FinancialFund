package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

public class AccountModel {
    @SerializedName("Name")
    public String Name;
    @SerializedName("AccountId")
    public String AccountId;
     @SerializedName("AccountType")
    public String AccountType;

}
