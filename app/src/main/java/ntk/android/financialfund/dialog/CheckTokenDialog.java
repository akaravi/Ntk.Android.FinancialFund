package ntk.android.financialfund.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import es.dmoral.toasty.Toasty;
import io.reactivex.annotations.NonNull;
import ntk.android.base.activity.BaseActivity;
import ntk.android.base.config.NtkObserver;
import ntk.android.base.config.ServiceExecute;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.base.utill.prefrense.Preferences;
import ntk.android.financialfund.R;
import ntk.android.financialfund.db.FoundInfo;
import ntk.android.financialfund.server.model.ClientTokenModel;
import ntk.android.financialfund.server.model.OrderTokenRequestModel;
import ntk.android.financialfund.server.model.OrderUserToken;
import ntk.android.financialfund.server.model.UserToken;
import ntk.android.financialfund.server.service.AuthFundsService;
import ntk.android.financialfund.server.service.ConfigFundsHeaders;
import ntk.android.financialfund.view.FundCaptchaView;

public class CheckTokenDialog extends BaseActivity {
    public static final String EXTRA_CLASSNAME = "Extra_className";
    Class activity;
    private Runnable runnable;
    private String mobileNumber;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_checktoken);
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
        ((TextView) findViewById(R.id.txtToolbar)).setText("احراز هویت");
        activity = (Class) getIntent().getExtras().get(EXTRA_CLASSNAME);

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
        findViewById(R.id.sub_auth_loading).setVisibility(View.GONE);
        findViewById(R.id.sub_get_token).setVisibility(View.VISIBLE);
        checkTokenApi();
    }


    private void checkTokenApi() {
        ServiceExecute.execute(
                new AuthFundsService(this).checkToken()).
                subscribe(new NtkObserver<ErrorException<ClientTokenModel>>() {
                    @Override
                    public void onNext(@NonNull ErrorException<ClientTokenModel> response) {
                        if (response.IsSuccess)
                            startActivity(new Intent(CheckTokenDialog.this, activity));
                        else {
                            OrderToken();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        switcher.showErrorView("خطا رخ داد" + "\n" + e.getCause(), () -> checkTokenApi());
                    }
                });
    }

    private void OrderToken() {
        findViewById(R.id.sub_auth_mobile).setVisibility(View.VISIBLE);
        findViewById(R.id.sub_get_token).setVisibility(View.GONE);
        findViewById(R.id.sub_auth_sms).setVisibility(View.GONE);
        findViewById(R.id.sub_auth_loading).setVisibility(View.GONE);
        String mobile = Preferences.with(CheckTokenDialog.this).UserInfo().mobile();
        ((TextView) findViewById(R.id.tvMobile)).setText("به شماره ی " + mobile + " کد تایید عملیات ارسال می شود");
        ((FundCaptchaView) findViewById(R.id.fundCaptchaView)).getNewCaptcha();
        findViewById(R.id.btnSend).setOnClickListener(view -> OrderTokenApi());

    }

    private void OrderTokenApi() {
        FundCaptchaView captcha = findViewById(R.id.fundCaptchaView);

        if (captcha.getCaptchaText().trim().equalsIgnoreCase("")) {
            Toasty.warning(this, "متن تصویر را وارد کنید", Toasty.LENGTH_LONG, true).show();
            return;
        }
        findViewById(R.id.sub_auth_loading).setVisibility(View.VISIBLE);
        OrderTokenRequestModel req = new OrderTokenRequestModel();
        req.mobileNumber = mobileNumber = Preferences.with(CheckTokenDialog.this).UserInfo().mobile();
        req.captchaKey = captcha.getCaptchaKey();
        req.captchaValue = captcha.getCaptchaText();
        req.uniqueDeviceId = ConfigFundsHeaders.GET_DEVICE_ID(this);
        ServiceExecute.execute(
                new AuthFundsService(this).orderToken(req)).
                subscribe(new NtkObserver<ErrorException<OrderUserToken>>() {
                    @Override
                    public void onNext(@NonNull ErrorException<OrderUserToken> orderUserTokenErrorException) {
                        findViewById(R.id.sub_auth_loading).setVisibility(View.GONE);
                        if (orderUserTokenErrorException.IsSuccess)
                            getToken();
                        else {
                            ((FundCaptchaView) findViewById(R.id.fundCaptchaView)).getNewCaptcha();
                            Toasty.error(CheckTokenDialog.this, orderUserTokenErrorException.ErrorMessage, Toasty.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ((FundCaptchaView) findViewById(R.id.fundCaptchaView)).getNewCaptcha();
                        findViewById(R.id.sub_auth_loading).setVisibility(View.GONE);
                        switcher.showErrorView("خطا رخ داد" + "\n" + e.getCause(), () -> OrderTokenApi());
                    }

                });
    }

    private void getToken() {
        findViewById(R.id.sub_auth_sms).setVisibility(View.VISIBLE);
        findViewById(R.id.sub_auth_mobile).setVisibility(View.GONE);
        findViewById(R.id.sub_auth_loading).setVisibility(View.GONE);
        ((FundCaptchaView) findViewById(R.id.fundCaptchaSmsView)).getNewCaptcha();
        findViewById(R.id.btnSendSms).setOnClickListener(view -> getTokenApi());
    }

    private void getTokenApi() {
        //create Ui
        EditText smsValue = findViewById(R.id.txtSmsValue);
        FundCaptchaView captcha = findViewById(R.id.fundCaptchaSmsView);
        if (smsValue.getText().toString().equalsIgnoreCase("")) {
            Toasty.warning(this, "کد اعتبار سنجی که برایتان ارسال شده را وارد کنید", Toasty.LENGTH_LONG, true).show();
            return;
        }
        findViewById(R.id.sub_auth_loading).setVisibility(View.VISIBLE);
        UserToken req = new UserToken();
        req.packageName = ConfigFundsHeaders.GET_PACKAGENAME();
        req.mobileNumber = mobileNumber;
        req.smsValue = smsValue.getText().toString();
        req.captchaValue = captcha.getCaptchaText();
        req.captchaKey = captcha.getCaptchaKey();
        req.uniqueDeviceId = ConfigFundsHeaders.GET_DEVICE_ID(this);
        ServiceExecute.execute(
                new AuthFundsService(this).getToken(req)).
                subscribe(new NtkObserver<ErrorException<ClientTokenModel>>() {
                    @Override
                    public void onNext(@NonNull ErrorException<ClientTokenModel> response) {
                        findViewById(R.id.sub_auth_loading).setVisibility(View.GONE);
                        if (response.IsSuccess) {
                            finish();
                            startActivity(new Intent(CheckTokenDialog.this, activity));

                        } else {
                            ((FundCaptchaView) findViewById(R.id.fundCaptchaSmsView)).getNewCaptcha();
                            Toasty.error(CheckTokenDialog.this, response.ErrorMessage, Toasty.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        findViewById(R.id.sub_auth_loading).setVisibility(View.GONE);
                        ((FundCaptchaView) findViewById(R.id.fundCaptchaSmsView)).getNewCaptcha();
                        switcher.showErrorView("خطا رخ داد" + "\n" + e.getCause(), () -> OrderTokenApi());
                    }

                });
    }


}
