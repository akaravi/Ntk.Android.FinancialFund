package ntk.android.financialfund.server.service;

import java.util.Map;

import io.reactivex.Observable;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.financialfund.server.model.FundBranchAccount;
import ntk.android.financialfund.server.model.AccountToAccountModel;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Path;

interface IAccountFund {
    @GET("{cpath}")
    @Headers({"content-type: application/json"})
    Observable<ErrorException<FundBranchAccount>> GetOne(@Path(value = "cpath",encoded = true) String cpath, @HeaderMap Map<String, String> headers);

    @GET("{cpath}")
    @Headers({"content-type: application/json"})
    Observable<ErrorException<FundBranchAccount>> GetList(@Path(value = "cpath",encoded = true) String cpath, @HeaderMap Map<String, String> headers);

  @GET("{cpath}")
    @Headers({"content-type: application/json"})
    Observable<ErrorException<FundBranchAccount>> AccountToAccount(@Path(value = "cpath",encoded = true) String cpath, @HeaderMap Map<String, String> headers, @Body AccountToAccountModel body);



//            AccountToAccount
//
//    account data
//
//    /// <summary>
//    /// این مقدار فقط سمت اندروید خوانده می شود و نگهداری می شود
//    /// پر شدن این مقدار در برنامه رابط دبلیو پی اف سمت شعبه است
//    /// </summary>
//    public string BranchGuidKey { get; set; }
//
//    /// <summary>
//    /// کلید توکن اکانت در برنامه قرض الحسنه
//    /// محاسباتی از سمت برنامه قرض الحسنه ما فقط نگه می داریم
//    /// توکن یک حساب در قرض الحسنه است
//    /// </summary>
//    public string AccountKey { get; set; }
//
//    /// <summary>
//    ///آی دی نوع حساب
//    /// </summary>
//    public int AccountTypeId { get; set; }
//
//
//    /// <summary>
//    /// نوع حساب
//    /// نمایش در اندروید
//    /// </summary>
//    public string AccountTypeDescription { get; set; }
//
//    /// <summary>
//    /// آیا حساب مشترک است
//    /// حساب چند امضاء دارد
//    /// نمایش در اندروید
//    /// </summary>
//    public bool IsShared { get; set; }
//
//
//    /// <summary>
//    /// شماره حساب
//    /// نمایش در اندروید
//    /// </summary>
//    public string AccountNumber { get; set; }
//
//
//    /// <summary>
//    /// آیا حساب مربوط به وام است
//    /// نیاز به نمایش نیست
//    /// حساب هایی که مربوط به وام است در قسمت وام نمایش داده می شوند
//    /// کاربر اجازه سپرده به سپرده بر روی وام به حساب یا حساب به وام را ندارد
//    /// </summary>
//    public bool IsLoanAccount { get; set; }
//
//
//    /// <summary>
//    /// توضیحات
//    /// فیلد نمایشی
//    /// </summary>
//    public string Description { get; set; }
//
//    /// <summary>
//    /// عنوان حساب - صاحبان حساب چه کسانی هستند
//    /// نمایشی
//    /// حساب قرض الحسنه ویژه علی احمدی - حسن محمودی به شماره 22356565
//    /// </summary>
//    public string AccountClientDescription { get; set; }
//
//
//    /// <summary>
//    /// امتیاز حساب
//    /// نمایشی
//    /// </summary>
//    public int Score { get; set; }
//
//
//    /// <summary>
//    /// وضعیت حساب
//    /// حساب های غیر فعال اجازه انجام هیچ عملیاتی را ندارند
//    /// فقط عنوان حساب به صورت کم رنگ نمایش داده می شود با وضعیت بسته
//    /// فعال
//    /// بسته
//    /// </summary>
//    public bool IsActive { get; set; }
//
//    /// <summary>
//    /// موجودی حساب
//    /// مبلغ مانده وام در صورتی که حساب از نوع وام باشد
//    /// </summary>
//    public decimal Inventory { get; set; }
//
//    /// <summary>
//    /// مبلغ مانده اقساط
//    /// برای حساب هایی که نوع وام هستند
//    /// </summary>
//    public decimal LoanInstallmentRemain { get; set; }
//
//    /// <summary>
//    /// مبلغ اقساطی که کاربر باید پرداخت کند
//    /// </summary>
//    public decimal LoanInstallmentPrice { get; set; }
//
//    /// <summary>
//    /// تعداد اقساط سررسید گذشته
//    /// پرداخت نشده عقب افتاده
//    /// این تعداد ربطی به پرداخت قسط ندارد و فقط در قسمت وام نشان داده می شود که چند قسط از کاربر عقب افتاده است
//    /// </summary>
//    public int LoanInstallmentDelayCount { get; set; }
//
//    /// <summary>
//    /// مبلغ اقساط عقب افتاده به همراه مبلغ دیر کرد
//    /// </summary>
//    public decimal LoanInstallmentDelayPrice { get; set; }
//
//
//    /// <summary>
//    /// این عدد کمک می کند تا رنگ بندی اقساط مشخص گردد
//    /// در ظاهر یو آی در قسمت اقساط
//    /// 0 وام
//    /// </summary>
//    public LoanStatus LoanStatusAlert { get; set; }
//
//    public enum LoanStatus
//    {
//        /// <summary>
//        /// درخواست شده
//        /// </summary>
//        Requested=0,
//        /// <summary>
//        /// نوبت دهی جهت تایید یا رد درخواست وام
//        /// </summary>
//        SetDateToCheckLoan=1,
//        /// <summary>
//        /// نوبت دهی شده جهت ارائه مدارک
//        /// </summary>
//        SetDateToGetCollatoral = 2,
//        /// <summary>
//        /// نوبت دهی شده جهت چک کردن ضمانت نامه ها
//        /// </summary>
//        SetDateToCheckCollatoral=3,
//        /// <summary>
//        /// نوبت دهی شده جهت پرداخت وام
//        /// </summary>
//        SetDateToPay=4,
//        //**********************
//        // قسمت بالا همگی مربوط به درخواست وام است
//        /// <summary>
//        /// پرداخت شده
//        /// رنگ یوآی سبز
//        /// بقیه رنگ ها هماهنگ شود
//        /// </summary>
//        Payed=5,
//        /// <summary>
//        /// اقساط سررسید گذشته دارد
//        /// </summary>
//        HasWasPast = 6,
//        /// <summary>
//        /// اقساط معوق دارد
//        /// </summary>
//        HasDelay = 7,
//        /// <summary>
//        /// اقساط مشکوک الوصول دارد
//        /// </summary>
//        HasDoubtful=8,
//        /// <summary>
//        /// وام تسویه شده است
//        /// </summary>
//        HasFinished=9,


    }

