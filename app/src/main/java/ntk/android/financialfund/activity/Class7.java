package ntk.android.financialfund.activity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import es.dmoral.toasty.Toasty;
import io.reactivex.annotations.NonNull;
import ntk.android.base.activity.BaseActivity;
import ntk.android.base.config.NtkObserver;
import ntk.android.base.config.ServiceExecute;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.base.entitymodel.base.FilterModel;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.AccountSelectAdapter;
import ntk.android.financialfund.server.model.AccountModel;
import ntk.android.financialfund.server.service.AccountService;

public class Class7 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class7);
        ((TextView) findViewById(R.id.txtToolbar)).setText(getString(R.string.mainCard7));
        ((Button) findViewById(R.id.btnOk)).setText("گزارش");
        findViewById(R.id.btn_cancel).setOnClickListener(view -> finish());
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
        getAccounts();
        findViewById(R.id.btnOk).setOnClickListener(view -> checkData());
    }

    private void checkData() {
        AutoCompleteTextView accountId = findViewById(R.id.etAccountId);
        TextInputEditText fromDate = findViewById(R.id.etFromDate);
        TextInputEditText toDate = findViewById(R.id.etToDate);
        if (accountId.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا حساب سپرده ی خود را انتخاب کنید").show();
        else if (fromDate.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا ابتدای بازی زمانی خود را انتخاب نمایید").show();
        else if (toDate.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا انتهای بازی زمانی خود را انتخاب نمایید").show();
        else
            callApi();

    }

    private void callApi() {

    }

    private void getAccounts() {
        switcher.showProgressView();
        ServiceExecute.execute(new AccountService(this).getAll(new FilterModel())).subscribe(new NtkObserver<ErrorException<AccountModel>>() {
            @Override
            public void onNext(@NonNull ErrorException<AccountModel> accountModelErrorException) {
                switcher.showContentView();
                AutoCompleteTextView paymentType = (AutoCompleteTextView) findViewById(R.id.etAccountId);
                TextInputEditText Name = findViewById(R.id.etName);
                paymentType.setAdapter(new AccountSelectAdapter(Class7.this, accountModelErrorException.ListItems));
                paymentType.setOnItemClickListener((adapterView, view12, i, l) -> {
                    if (i >= 0) {
                        paymentType.setText(((AccountModel) adapterView.getItemAtPosition(i)).AccountId);
                        Name.setText(((AccountModel) adapterView.getItemAtPosition(i)).Name);
                    } else {
                        paymentType.setText("");
                        Name.setText("");
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable e) {
                switcher.showErrorView(e.getMessage(), Class7.this::getAccounts);
            }
        });
    }
}
