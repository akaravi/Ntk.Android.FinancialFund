package ntk.android.financialfund.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import ntk.android.base.config.NtkObserver;
import ntk.android.base.config.ServiceExecute;
import ntk.android.base.entitymodel.base.CaptchaModel;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.financialfund.R;
import ntk.android.financialfund.server.service.AuthFundsService;

public class FundCaptchaView extends FrameLayout {

    private CaptchaModel captcha;

    public FundCaptchaView(Context context) {
        this(context, null);
    }

    public FundCaptchaView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.sub_base_captcha, this);
        inflate.findViewById(R.id.imgCaptcha).setOnClickListener(v -> getNewCaptcha());

    }

    public void getNewCaptcha() {
        ServiceExecute.execute(new AuthFundsService(getContext()).getCaptcha())
                .subscribe(new NtkObserver<ErrorException<CaptchaModel>>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ErrorException<CaptchaModel> captchaResponce) {
                        if (captchaResponce.IsSuccess) {
                            ImageLoader.getInstance().displayImage(captchaResponce.Item.Image, (ImageView) findViewById(R.id.imgCaptcha));
                            captcha = captchaResponce.Item;
                        } else {
                            ((ImageView) findViewById(R.id.imgCaptcha)).setImageResource(R.drawable.error_captcha);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        ((ImageView) findViewById(R.id.imgCaptcha)).setImageResource(R.drawable.error_captcha);

                    }

                });
    }


    public String getCaptchaText() {
        return ((EditText) findViewById(R.id.txtCaptcha)).getText().toString();
    }

    public String getCaptchaKey() {
        if (captcha != null)
            return captcha.Key;
        return "";
    }

    public void clearCaptchaText() {
        ((EditText) findViewById(R.id.txtCaptcha)).setText("");
    }
}