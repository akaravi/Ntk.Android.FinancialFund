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

public class Class5 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class5);
        ((TextView) findViewById(R.id.txtToolbar)).setText(getString(R.string.mainCard5));
        ((Button) findViewById(R.id.btnOk)).setText("ارسال درخواست");
        findViewById(R.id.btn_cancel).setOnClickListener(view -> finish());
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
        getAccounts();
        findViewById(R.id.btnOk).setOnClickListener(view -> checkData());
    }

    private void checkData() {
        AutoCompleteTextView accountId = findViewById(R.id.etAccountId);
        TextInputEditText amount = findViewById(R.id.etAmount);
        if (accountId.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا حساب سپرده ی خود را انتخاب کنید").show();
        else if (amount.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "مبلغ تسهیلات مورد نظر خود را مشخص نمایید").show();
        else {
            long price = 0;
            try {
                price = Long.parseLong(amount.getText().toString());
            } catch (Exception e) {
                Toasty.error(this, "مبلغ واریزی نا معتبر است").show();
                return;
            }
            if (price < 100) {
                Toasty.error(this, "حداقل مبلغ تسهیلات 100 ریال می باشد").show();
            } else
                callApi();
        }

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
                paymentType.setAdapter(new AccountSelectAdapter(Class5.this, accountModelErrorException.ListItems));
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
                switcher.showErrorView(e.getMessage(), Class5.this::getAccounts);
            }
        });
    }
}
