package ntk.android.financialfund.server.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FundAccountReport {

    public double ActualRemain;

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


    public static List<FundAccountReport> ACTUAL_REPORT(List<FundAccountReport> listItems) {
        if (listItems == null)
            return new ArrayList<>();
        if (listItems.size() == 0)
            return listItems;

        FundAccountReport remain = listItems.get(0);
        if (remain.Creditor != 0)
            remain.ActualRemain = remain.Creditor;
        if (remain.Debtor != 0)
            remain.ActualRemain = remain.Debtor;
        remain.Debtor = 0;
        remain.Creditor = 0;
//        FundAccountReport lastRecord = listItems.remove(listItems.size() - 1);
        for (int i = 1; i <= listItems.size() - 2; i++) {
            listItems.get(i).ActualRemain = listItems.get(i - 1).ActualRemain - listItems.get(i).Creditor + listItems.get(i).Debtor;
        }
        return listItems;
    }
}
          
