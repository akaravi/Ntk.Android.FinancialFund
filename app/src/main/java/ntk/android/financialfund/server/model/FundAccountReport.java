package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

public class FundAccountReport {
    // تاریخ عملیات به فارس
    @SerializedName("PersianActionDate")
    public String PersianActionDate;

    @SerializedName("Title")
    public String Title;

    // توضیحات
    @SerializedName("Description")
    public String Description;

    /// مبلغ بدهکار
    @SerializedName("Debtor")
    public double Debtor;

    /// مبلغ بستانکار
    @SerializedName("Creditor")
    public double Creditor;

    /// جمع ردیف های بدهکار تا این ردیف
    @SerializedName("SumRowDebtor")
    public double SumRowDebtor;

    /// جمع ردیف های بستانکار تا این ردیف
    @SerializedName("SumRowCreditor")
    public double SumRowCreditor;

    // مانده تا این ردی
    @SerializedName("SumRemain")
    public double SumRemain;

    //رنگ فونت نوشته ها
    // معمولی صفر
    // سبز یک
    // قرمز دو
    // خاکستری سه
    // آبی چهار
    @SerializedName("RowColor")
    public int RowColor;


}
          
