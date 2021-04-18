package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

public class FundBranchAccount extends BaseFundEntity<Long> {
    public static final long LIMIT_PRISE = 1000;
    @SerializedName("BranchGuidKey")
    public String branchGuidKey;
    @SerializedName("AccountKey")
    public String accountKey;
    @SerializedName("AccountTypeId")
    public long accountTypeId;
    @SerializedName("AccountTypeDescription")
    public String accountTypeDescription;
    @SerializedName("IsShared")
    public boolean isShared;
    @SerializedName("AccountNumber")
    public boolean accountNumber;
    @SerializedName("IsLoanAccount")
    public boolean isLoanAccount;
    @SerializedName("Description")
    public String description;
    @SerializedName("AccountClientDescription")
    public String accountClientDescription;
    @SerializedName("Score")
    public long score;
    @SerializedName("IsActive")
    public boolean isActive;
    @SerializedName("Inventory")
    public long inventory;
    @SerializedName("LoanInstallmentRemain")
    public long loanInstallmentRemain;
    @SerializedName("LoanInstallmentPrice")
    public long loanInstallmentPrice;
    @SerializedName("LoanInstallmentDelayCount")
    public long loanInstallmentDelayCount;
    @SerializedName("LoanInstallmentDelayPrice")
    public long loanInstallmentDelayPrice;
    @SerializedName("LoanStatusAlert")
    public long loanStatusAlert;
}
