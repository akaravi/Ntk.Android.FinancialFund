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
import ntk.android.financialfund.adapter.LoanSelectAdapter;
import ntk.android.financialfund.server.model.LoanModel;
import ntk.android.financialfund.server.service.LoanService;

public class Class6 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class6);
        ((TextView) findViewById(R.id.txtToolbar)).setText(getString(R.string.mainCard6));
        ((Button) findViewById(R.id.btnOk)).setText("گزارش");
        findViewById(R.id.btn_cancel).setOnClickListener(view -> finish());
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
        getLoans();
        findViewById(R.id.btnOk).setOnClickListener(view -> checkData());
    }

    private void getLoans() {
        switcher.showProgressView();
        ServiceExecute.execute(new LoanService(this).getAll(new FilterDataModel())).subscribe(new NtkObserver<ErrorException<LoanModel>>() {
            @Override
            public void onNext(@NonNull ErrorException<LoanModel> accountModelErrorException) {
                switcher.showContentView();
                AutoCompleteTextView loanEt = (AutoCompleteTextView) findViewById(R.id.etLoan);
                TextInputEditText Name = findViewById(R.id.etName);
                TextInputEditText Amount = findViewById(R.id.etAmount);
                loanEt.setAdapter(new LoanSelectAdapter(Class6.this, accountModelErrorException.ListItems));
                loanEt.setOnItemClickListener((adapterView, view12, i, l) -> {
                    if (i >= 0) {
                        loanEt.setText(((LoanModel) adapterView.getItemAtPosition(i)).Name);
                        Name.setText(((LoanModel) adapterView.getItemAtPosition(i)).AccountId);
                    } else {
                        loanEt.setText("");
                        Name.setText("");
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable e) {
                switcher.showErrorView(e.getMessage(), Class6.this::getLoans);
            }
        });
    }

    private void checkData() {
        AutoCompleteTextView loanEt = findViewById(R.id.etLoan);
        TextInputEditText fromDate = findViewById(R.id.etFromDate);
        TextInputEditText toDate = findViewById(R.id.etToDate);
        if (loanEt.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا وام خود را انتخاب کنید").show();
        else if (fromDate.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا ابتدای بازی زمانی خود را انتخاب نمایید").show();
        else if (toDate.getText().toString().equalsIgnoreCase(""))
            Toasty.error(this, "لطفا انتهای بازی زمانی خود را انتخاب نمایید").show();
        else
            callApi();
    }


    private void callApi() {

    }
}
