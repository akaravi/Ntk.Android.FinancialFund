package ntk.android.financialfund.server.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import ntk.android.base.config.ConfigRestHeader;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.base.entitymodel.base.FilterDataModel;
import ntk.android.financialfund.server.model.LoanModel;

public class LoanService {
    private final Map<String, String> headers;
    String baseUrl = "api/v1/";
    String controlerUrl = "service";
    Context context;

    public LoanService(Context context) {
        this.context = context;
        this.headers = (new ConfigRestHeader()).GetHeaders(context);
    }

    public Observable<ErrorException<LoanModel>> getAll(FilterDataModel request) {
        return Observable.create(emitter -> {
            Thread.sleep(2000);
            ErrorException<LoanModel> model = new ErrorException<>();
            model.IsSuccess = true;
            model.ListItems = new ArrayList<>();
            {
                LoanModel a1 = new LoanModel();
                a1.Name = "وام خرید خودرو";
                a1.AccountId = "آیدین صلواتی-123456789";
                a1.Amount = 5000000;
                a1.payment = 50000;
                model.ListItems.add(a1);
            }
            {
                LoanModel a1 = new LoanModel();
                a1.Name = "آیدین صلواتی-وام جعاله";
                a1.AccountId = "آیدین صلواتی-123456789";
                a1.Amount = 5000000;     a1.payment = 50000;
                model.ListItems.add(a1);
            }
            model.TotalRowCount = 2;
            emitter.onNext(model);
            emitter.onComplete();
        });
    }


}
