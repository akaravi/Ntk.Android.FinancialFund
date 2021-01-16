package ntk.android.financialfund.server.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import ntk.android.base.config.ConfigRestHeader;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.base.entitymodel.base.FilterDataModel;
import ntk.android.financialfund.server.model.AccountModel;

public class AccountService {
    private final Map<String, String> headers;
    String baseUrl = "api/v1/";
    String controlerUrl = "service";
    Context context;

    public AccountService(Context context) {
        this.context = context;
        this.headers = (new ConfigRestHeader()).GetHeaders(context);
    }

    public Observable<ErrorException<AccountModel>> getAll(FilterDataModel request) {
        return Observable.create(emitter -> {
            Thread.sleep(2000);
            ErrorException<AccountModel> model = new ErrorException<>();
            model.IsSuccess = true;
            model.ListItems = new ArrayList<>();
            {
                AccountModel a1 = new AccountModel();
                a1.Name = "آیدین صلواتی";
                a1.AccountId = "123456789";
                a1.AccountType = "جاری";
                model.ListItems.add(a1);
            }
            {
                AccountModel a1 = new AccountModel();
                a1.Name = "آیدین صلواتی-مشترک با حسن کریمی";
                a1.AccountId = "123456789";
                a1.AccountType = "حساب مشترک";
                model.ListItems.add(a1);
            }
            model.TotalRowCount = 2;
            emitter.onNext(model);
            emitter.onComplete();
        });
    }


    public Observable<ErrorException<AccountModel>> getPopularDestinationsAccount(FilterDataModel request) {
        return Observable.create(emitter -> {
            Thread.sleep(2000);
            ErrorException<AccountModel> model = new ErrorException<>();
            model.IsSuccess = true;
            model.ListItems = new ArrayList<>();
            {
                AccountModel a1 = new AccountModel();
                a1.Name = "حسین متقی";
                a1.AccountId = "132423423562";
                a1.AccountType = "جاری";
                model.ListItems.add(a1);
            }
            {
                AccountModel a1 = new AccountModel();
                a1.Name = "حجت سلامی زاده";
                a1.AccountId = "1234333456789";
                a1.AccountType = "حساب مشترک";
                model.ListItems.add(a1);
            }
            {
                AccountModel a1 = new AccountModel();
                a1.Name = "حسین نامداری";
                a1.AccountId = "78678342";
                a1.AccountType = "حساب کوتاه مدت";
                model.ListItems.add(a1);
            }

            model.TotalRowCount = 2;
            emitter.onNext(model);
            emitter.onComplete();
        });
    }
}
