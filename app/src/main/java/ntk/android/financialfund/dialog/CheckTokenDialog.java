package ntk.android.financialfund.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import es.dmoral.toasty.Toasty;
import ntk.android.base.activity.BaseActivity;
import ntk.android.base.config.ErrorExceptionObserver;
import ntk.android.base.config.ServiceExecute;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.base.view.CaptchaView;
import ntk.android.financialfund.R;
import ntk.android.financialfund.db.FoundInfo;
import ntk.android.financialfund.server.model.OrderTokenRequestModel;
import ntk.android.financialfund.server.model.OrderUserToken;
import ntk.android.financialfund.server.service.AuthFundsService;
import ntk.android.financialfund.view.FundCaptchaView;

public class CheckTokenDialog extends BaseActivity {
    Class activity;
    private Runnable runnable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_checktoken);
        String token = new FoundInfo(this).getToken();
        if (token.equalsIgnoreCase("")) {
            OrderToken();
        } else {
            checkToken();
        }

    }


    private void checkToken() {

    }

    private void OrderToken() {
        findViewById(R.id.sub_auth_mobile).setVisibility(View.VISIBLE);
        findViewById(R.id.sub_auth_sms).setVisibility(View.GONE);
        findViewById(R.id.btnSend).setOnClickListener(view -> OrderTokenApi());

    }

    private void OrderTokenApi() {
        EditText mobileTxt = findViewById(R.id.txtActRegister);
        FundCaptchaView captcha = findViewById(R.id.fundCaptchaView);
        if (mobileTxt.getText().toString().equalsIgnoreCase("")) {
            Toasty.warning(this, "شماره تلفن همراه را وارد کنید", Toasty.LENGTH_LONG, true).show();
            return;
        } else if (!mobileTxt.getText().toString().startsWith("09")) {
            Toasty.warning(this, "شماره تلفن همراه را به صورت صحیح وارد کنید", Toasty.LENGTH_LONG, true).show();
            return;
        } else if (captcha.getCaptchaText().equalsIgnoreCase("")) {
            Toasty.warning(this, "شماره تلفن همراه را به صورت صحیح وارد کنید", Toasty.LENGTH_LONG, true).show();
            return;
        }

        OrderTokenRequestModel req = new OrderTokenRequestModel();
        req.mobileNumber = mobileTxt.getText().toString();
        req.captchaKey = captcha.getCaptchaKey();
        req.captchaValue = captcha.getCaptchaText();
        ServiceExecute.execute(
                new AuthFundsService(this).orderToken(req)).
                subscribe(new ErrorExceptionObserver<OrderUserToken>(switcher::showErrorView) {
                    @Override
                    protected void SuccessResponse(ErrorException<OrderUserToken> orderUserTokenErrorException) {

                    }

                    @Override
                    protected void failResponse(ErrorException<OrderUserToken> orderUserTokenErrorException) {
                        //todo renew Captcha
                        super.failResponse(orderUserTokenErrorException);
                    }

                    @Override
                    protected Runnable tryAgainMethod() {
                        return null;
                    }

                });
    }


}
