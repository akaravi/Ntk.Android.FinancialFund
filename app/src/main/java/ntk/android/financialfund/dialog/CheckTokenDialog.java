package ntk.android.financialfund.dialog;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import es.dmoral.toasty.Toasty;
import io.reactivex.annotations.NonNull;
import ntk.android.base.Extras;
import ntk.android.base.activity.BaseActivity;
import ntk.android.base.config.NtkObserver;
import ntk.android.base.config.ServiceExecute;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.base.utill.prefrense.Preferences;
import ntk.android.financialfund.R;
import ntk.android.financialfund.db.FoundInfo;
import ntk.android.financialfund.server.FundSmsReceiver;
import ntk.android.financialfund.server.model.ClientTokenModel;
import ntk.android.financialfund.server.model.OrderTokenRequestModel;
import ntk.android.financialfund.server.model.OrderUserToken;
import ntk.android.financialfund.server.model.UserToken;
import ntk.android.financialfund.server.service.AuthFundsService;
import ntk.android.financialfund.server.service.ConfigFundsHeaders;
import ntk.android.financialfund.view.FundCaptchaView;

public class CheckTokenDialog extends BaseActivity implements FundSmsReceiver.SmsReceived {
    private static final int REQ_PERMISSION = 102;
    public static final String EXTRA_CLASSNAME = "Extra_className";
    Class activity;
    FundSmsReceiver receiver = new FundSmsReceiver(this);
    private String mobileNumber;
    private boolean isRegisteredBroadcast = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_checktoken);
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
        ((TextView) findViewById(R.id.txtToolbar)).setText("احراز هویت");
        activity = (Class) getIntent().getExtras().get(EXTRA_CLASSNAME);

//        startActivity(new Intent(CheckTokenDialog.this, activity));
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
        findViewById(R.id.btnSend).setOnClickListener(view -> {
            FundCaptchaView captcha = findViewById(R.id.fundCaptchaView);

            if (captcha.getCaptchaText().trim().equalsIgnoreCase("")) {
                Toasty.warning(this, "متن تصویر را وارد کنید", Toasty.LENGTH_LONG, true).show();
                return;
            }
            if (CheckPermission()) {
                registerListener();
                OrderTokenApi();
            } else {
                ActivityCompat.requestPermissions(CheckTokenDialog.this, new String[]{Manifest.permission.RECEIVE_SMS}, REQ_PERMISSION);
            }
        });
    }

    private boolean CheckPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            return checkSelfPermission(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private void OrderTokenApi() {
        FundCaptchaView captcha = findViewById(R.id.fundCaptchaView);
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
                            captcha.clearCaptchaText();
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
        if (captcha.getCaptchaText().trim().equalsIgnoreCase("")) {
            Toasty.warning(this, "متن تصویر را وارد کنید", Toasty.LENGTH_LONG, true).show();
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
                            captcha.clearCaptchaText();
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

    private void registerListener() {
        if (!isRegisteredBroadcast) {
            isRegisteredBroadcast = true;
            IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            registerReceiver(receiver, filter);
        }
    }
    private void unregisterListener() {
        if (isRegisteredBroadcast) {
            unregisterReceiver(receiver);
            isRegisteredBroadcast = false;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
      unregisterListener();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, ntk.android.base.R.string.plz_insert_code_login, Toast.LENGTH_SHORT).show();
                    OrderTokenApi();
                } else {
                    registerListener();
                    OrderTokenApi();
                }
                break;
        }
    }

    @Override
    public void onReceive(String code) {
            EditText smsValue = findViewById(R.id.txtSmsValue);
            smsValue.setText(code);
    }
}
