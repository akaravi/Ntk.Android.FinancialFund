package ntk.android.financialfund.activity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import es.dmoral.toasty.Toasty;
import io.reactivex.annotations.NonNull;
import ntk.android.base.activity.BaseActivity;
import ntk.android.base.config.ErrorExceptionObserver;
import ntk.android.base.config.NtkObserver;
import ntk.android.base.config.ServiceExecute;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.AccountReportAdapter;
import ntk.android.financialfund.adapter.AccountSelectAdapter;
import ntk.android.financialfund.server.model.FundAccountReport;
import ntk.android.financialfund.server.model.FundBranchAccount;
import ntk.android.financialfund.server.service.AccountFundsService;

public class AccountReportActivity extends BaseActivity {
    private FundBranchAccount source;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acount_report_activity);
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
        if (accountId.getText().toString().equalsIgnoreCase("")) {
            Toasty.error(this, "لطفا حساب سپرده ی خود را انتخاب کنید").show();
            return;
        }
        ServiceExecute.execute(new AccountFundsService(this).getOne(source.id))
                .subscribe(new NtkObserver<ErrorException<FundAccountReport>>() {
                    @Override
                    public void onNext(@NonNull ErrorException<FundAccountReport> response) {
                        switcher.showContentView();
                        if (response.IsSuccess) {
                            AccountReportAdapter adapter = new AccountReportAdapter(response.ListItems);
                            RecyclerView rc = (RecyclerView) findViewById(R.id.rc);
                            rc.setLayoutManager(new LinearLayoutManager(AccountReportActivity.this, RecyclerView.VERTICAL, false));
                            rc.setAdapter(adapter);
                        } else {
                            Toasty.error(AccountReportActivity.this, response.ErrorMessage, Toasty.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        switcher.showErrorView();
                    }
                });
        ;

    }

    private void getAccounts() {
        switcher.showProgressView();
        ServiceExecute.execute(new AccountFundsService(this).getList()).subscribe(new ErrorExceptionObserver<FundBranchAccount>(switcher::showErrorView) {
            @Override
            protected void SuccessResponse(ErrorException<FundBranchAccount> accountModelErrorException) {
                switcher.showContentView();
                AutoCompleteTextView sourceAccount = (AutoCompleteTextView) findViewById(R.id.etAccountId);

                TextInputEditText SourceName = findViewById(R.id.etName);
                sourceAccount.setAdapter(new AccountSelectAdapter(AccountReportActivity.this, accountModelErrorException.ListItems));
                sourceAccount.setOnItemClickListener((adapterView, view12, i, l) -> {
                    if (i >= 0) {
                        source = (((FundBranchAccount) adapterView.getItemAtPosition(i)));
                        sourceAccount.setText(((FundBranchAccount) adapterView.getItemAtPosition(i)).accountKey + "");
                        SourceName.setText(((FundBranchAccount) adapterView.getItemAtPosition(i)).accountClientDescription);
                    } else {
                        sourceAccount.setText("");
                        SourceName.setText("");
                    }
                });

            }

            @Override
            protected Runnable tryAgainMethod() {
                return () -> getAccounts();
            }
        });

    }
}
