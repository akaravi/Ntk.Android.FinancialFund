package ntk.android.financialfund.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import ntk.android.base.activity.BaseActivity;
import ntk.android.base.config.ErrorExceptionObserver;
import ntk.android.base.config.ServiceExecute;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.AccountSelectAdapter;
import ntk.android.financialfund.server.model.AccountToAccountModel;
import ntk.android.financialfund.server.model.FundBranchAccount;
import ntk.android.financialfund.server.service.AccountFundsService;

public class AccountToLoanActivity extends BaseActivity {
    private FundBranchAccount source;
    private FundBranchAccount loan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_to_loan_activty);
        ((TextView) findViewById(R.id.txtToolbar)).setText(getString(R.string.mainCard4));
        findViewById(R.id.btn_cancel).setOnClickListener(view -> finish());
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
        getAccounts();
        findViewById(R.id.btnOk).setOnClickListener(view -> checkData());
        ((TextInputEditText) findViewById(R.id.etAmount)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String input = s.toString();

                if (!input.isEmpty()) {

                    input = input.replace(",", "");

                    DecimalFormat format = new DecimalFormat("###,###,###,###");
                    String newPrice = format.format(Double.parseDouble(input));

                    TextInputEditText myEditText = findViewById(R.id.etAmount);
                    myEditText.removeTextChangedListener(this); //To Prevent from Infinite Loop
                    myEditText.setText(newPrice);
                    myEditText.setSelection(newPrice.length()); //Move Cursor to end of String
                    myEditText.addTextChangedListener(this);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
    }

    private void getAccounts() {
        switcher.showProgressView();
        ServiceExecute.execute(new AccountFundsService(this).getList()).subscribe(new ErrorExceptionObserver<FundBranchAccount>(switcher::showErrorView) {


            @Override
            protected void SuccessResponse(ErrorException<FundBranchAccount> accountModelErrorException) {
                switcher.showContentView();

                List<FundBranchAccount> accounts = new ArrayList<>();
                List<FundBranchAccount> loans = new ArrayList<>();
                for (int i = 0; i < accountModelErrorException.ListItems.size(); i++) {

                    if (accountModelErrorException.ListItems.get(i).isLoanAccount)
                        loans.add(accountModelErrorException.ListItems.get(i));
                    else
                        accounts.add(accountModelErrorException.ListItems.get(i));
                }
                if (accounts.isEmpty() || loans.isEmpty()) {
                    switcher.showEmptyView();
                } else {
                    AutoCompleteTextView sourceAccount = (AutoCompleteTextView) findViewById(R.id.etAccountId);
                    TextInputEditText SourceName = findViewById(R.id.etName);
                    sourceAccount.setAdapter(new AccountSelectAdapter(AccountToLoanActivity.this, accounts));
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
                    AutoCompleteTextView loanAccount = (AutoCompleteTextView) findViewById(R.id.etLoan);
                    TextInputEditText loanName = findViewById(R.id.etLoanName);
                    loanAccount.setAdapter(new AccountSelectAdapter(AccountToLoanActivity.this, loans));
                    loanAccount.setOnItemClickListener((adapterView, view12, i, l) -> {
                        if (i >= 0) {
                            loan = (((FundBranchAccount) adapterView.getItemAtPosition(i)));
                            loanAccount.setText(((FundBranchAccount) adapterView.getItemAtPosition(i)).accountKey + "");
                            loanName.setText(((FundBranchAccount) adapterView.getItemAtPosition(i)).accountClientDescription);
                        } else {
                            loanAccount.setText("");
                            loanName.setText("");
                        }
                    });
                }
            }

            @Override
            protected Runnable tryAgainMethod() {
                return () -> getAccounts();
            }
        });
    }

    private void checkData() {
        AutoCompleteTextView accountId = findViewById(R.id.etAccountId);
        TextInputEditText amount = findViewById(R.id.etAmount);
        AutoCompleteTextView loanEt = findViewById(R.id.etLoan);
        if (accountId.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا حساب سپرده ی خود را انتخاب کنید").show();
        else if (loanEt.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا وام خود را انتخاب کنید").show();
        else if (source == loan)
            Toasty.error(this, "حساب های انتخاب شده یکسان هستند").show();
        else if (amount.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "مبلغ واریزی را مشخص نمایید").show();
        else {
            long price = 0;
            try {
                String input = amount.getText().toString().replace(",", "");
                price = Long.parseLong(input);
            } catch (Exception e) {
                Toasty.error(this, "مبلغ واریزی نا معتبر است").show();
                return;
            }
            if (price < FundBranchAccount.LIMIT_PRISE) {
                Toasty.error(this, "حداقل مبلغ واریزی " + FundBranchAccount.LIMIT_PRISE + " ریال می باشد").show();
            } else
                confirmDilaog();
        }

    }

    private void confirmDilaog() {
        //todo open dialog
        callApi();
    }

    private void callApi() {
        TextInputEditText amount = findViewById(R.id.etAmount);
        AccountToAccountModel req = new AccountToAccountModel();
        req.fromFundBranchAccountId = source.id;
        req.toFundBranchAccountId = loan.id;
        req.description = "";

        req.amount = Long.parseLong(amount.getEditableText().toString().replace(",", ""));
        switcher.showProgressView();
        new AccountFundsService(this).accountToAccount(req).subscribe(new ErrorExceptionObserver<FundBranchAccount>(switcher::showErrorView) {
            @Override
            protected void SuccessResponse(ErrorException<FundBranchAccount> accountModelErrorException) {
                switcher.showContentView();

            }

            @Override
            protected Runnable tryAgainMethod() {
                return () -> callApi();
            }
        });
    }


}
