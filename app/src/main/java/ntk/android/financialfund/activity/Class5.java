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
import ntk.android.base.entitymodel.base.FilterDataModel;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.AccountSelectAdapter;
import ntk.android.financialfund.server.model.AccountModel;
import ntk.android.financialfund.server.service.AccountService;

public class Class5 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calss5);
        ((Button) findViewById(R.id.btnOk)).setText("گزارش");
        ((TextView) findViewById(R.id.txtToolbar)).setText(getString(R.string.mainCard5));
        findViewById(R.id.btn_cancel).setOnClickListener(view -> finish());
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
        getAccounts();
        findViewById(R.id.btnOk).setOnClickListener(view -> callApi());
    }

    private void callApi() {
        AutoCompleteTextView AccountId = (AutoCompleteTextView) findViewById(R.id.etPaymentType);
        if (AccountId.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا حساب خود را انتخاب کنید").show();


    }

    private void getAccounts() {
        switcher.showProgressView();
        ServiceExecute.execute(new AccountService(this).getAll(new FilterDataModel())).subscribe(new NtkObserver<ErrorException<AccountModel>>() {
            @Override
            public void onNext(@NonNull ErrorException<AccountModel> accountModelErrorException) {
                switcher.showContentView();
                AutoCompleteTextView paymentType = (AutoCompleteTextView) findViewById(R.id.etPaymentType);
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
