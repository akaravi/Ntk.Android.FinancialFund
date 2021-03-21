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
import ntk.android.financialfund.R;
import ntk.android.financialfund.db.FoundInfo;
import ntk.android.financialfund.server.model.ClientTokenModel;
import ntk.android.financialfund.server.model.GetTokenRequest;
import ntk.android.financialfund.server.model.OrderTokenRequestModel;
import ntk.android.financialfund.server.model.OrderUserToken;
import ntk.android.financialfund.server.model.UserToken;
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
        findViewById(R.id.sub_auth_mobile).setVisibility(View.GONE);
        findViewById(R.id.sub_auth_sms).setVisibility(View.GONE);
        findViewById(R.id.sub_auth_loading).setVisibility(View.VISIBLE);
        checkTokenApi();
    }

    private void OrderToken() {
        findViewById(R.id.sub_auth_mobile).setVisibility(View.VISIBLE);
        findViewById(R.id.sub_auth_sms).setVisibility(View.GONE);
        findViewById(R.id.sub_auth_loading).setVisibility(View.GONE);
        findViewById(R.id.btnSend).setOnClickListener(view -> OrderTokenApi());

    }
    private void checkTokenApi(){
        GetTokenRequest req=new GetTokenRequest();
        req.mobileNumber="09999999999";
        req.smsValue="11111";
        req.captchaValue="22222";
        req.captchaKey="4a7sU_aLetQ37MqS0S-7lg";
        req.token= new FoundInfo(this).getToken();
        ServiceExecute.execute(
                new AuthFundsService(this).checkToken(req)).
                subscribe(new ErrorExceptionObserver<ClientTokenModel>(switcher::showErrorView) {
                    @Override
                    protected void SuccessResponse(ErrorException<ClientTokenModel> orderUserTokenErrorException) {
                        //todo startActivity
                    }

                    @Override
                    protected void failResponse(ErrorException<ClientTokenModel> orderUserTokenErrorException) {
                        //todo renew Captcha
                        super.failResponse(orderUserTokenErrorException);
                    }

                    @Override
                    protected Runnable tryAgainMethod() {
                        return null;
                    }

                });
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
        }  else if (mobileTxt.getText().toString().length() != 11) {
            Toasty.warning(this, "شماره تلفن همراه را به صورت صحیح وارد کنید", Toasty.LENGTH_LONG, true).show();
            return;
        }else if (captcha.getCaptchaText().trim().equalsIgnoreCase("")) {
            Toasty.warning(this, "متن تصویر را وارد کنید", Toasty.LENGTH_LONG, true).show();
            return;
        }

        OrderTokenRequestModel req = new OrderTokenRequestModel();
        req.mobileNumber = mobileTxt.getText().toString();
        req.mobileNumber = "09999999999";
        req.captchaKey = captcha.getCaptchaKey();
        req.captchaValue = captcha.getCaptchaText();
        ServiceExecute.execute(
                new AuthFundsService(this).orderToken(req)).
                subscribe(new ErrorExceptionObserver<OrderUserToken>(switcher::showErrorView) {
                    @Override
                    protected void SuccessResponse(ErrorException<OrderUserToken> orderUserTokenErrorException) {
                        //todo go to sms sending
                        getTokenApi();
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

    private void getTokenApi() {
        UserToken req=new UserToken();
        req.mobileNumber="09999999999";
        req.smsValue="11111";
        req.captchaValue="22222";
        req.captchaKey="5SJ-4aaDfJxV2PZ5r8hD5w";
        ServiceExecute.execute(
                new AuthFundsService(this).getToken(req)).
                subscribe(new ErrorExceptionObserver<ClientTokenModel>(switcher::showErrorView) {
                    @Override
                    protected void SuccessResponse(ErrorException<ClientTokenModel> orderUserTokenErrorException) {
                        //todo startActivity
                    }

                    @Override
                    protected void failResponse(ErrorException<ClientTokenModel> orderUserTokenErrorException) {
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
