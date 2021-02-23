package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

public class FundBranchAccount extends BaseFundEntity<Long> {
    public static final long LIMIT_PRISE = 1000;
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
