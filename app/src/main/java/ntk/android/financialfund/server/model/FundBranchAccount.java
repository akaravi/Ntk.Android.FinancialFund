package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

public class FundBranchAccount {
    @SerializedName("id")
    public long id;
    @SerializedName("createdDate")
    public String createdDate;
    @SerializedName("createdBy")
    public String createdBy;
    @SerializedName("updatedDate")
    public String updatedDate;
    @SerializedName("updatedBy")
    public long updatedBy;
    @SerializedName("recordStatus")
    public long recordStatus;
    @SerializedName("branchGuidKey")
    public String branchGuidKey;
    @SerializedName("accountKey")
    public String accountKey;
    @SerializedName("accountTypeId")
    public long accountTypeId;
    @SerializedName("accountTypeDescription")
    public String accountTypeDescription;
    @SerializedName("isShared")
    public boolean isShared;
    @SerializedName("accountNumber")
    public boolean accountNumber;
    @SerializedName("isLoanAccount")
    public boolean isLoanAccount;
    @SerializedName("description")
    public String description;
    @SerializedName("accountClientDescription")
    public String accountClientDescription;
    @SerializedName("score")
    public long score;
    @SerializedName("isActive")
    public boolean isActive;
    @SerializedName("inventory")
    public long inventory;
    @SerializedName("loanInstallmentRemain")
    public long loanInstallmentRemain;
    @SerializedName("loanInstallmentPrice")
    public long loanInstallmentPrice;
    @SerializedName("loanInstallmentDelayCount")
    public long loanInstallmentDelayCount;
    @SerializedName("loanInstallmentDelayPrice")
    public long loanInstallmentDelayPrice;
    @SerializedName("loanStatusAlert")
    public long loanStatusAlert;

}
