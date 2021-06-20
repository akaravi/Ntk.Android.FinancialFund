package ntk.android.financialfund.activity;

import android.app.Dialog;
import android.content.Intent;
import android.provider.Settings;
import android.widget.EditText;

import ntk.android.base.ApplicationStaticParameter;
import ntk.android.base.BaseNtkApplication;
import ntk.android.base.NTKApplication;
import ntk.android.base.activity.common.BaseSplashActivity;
import ntk.android.base.config.RetrofitManager;
import ntk.android.base.utill.prefrense.Preferences;
import ntk.android.financialfund.R;
import ntk.android.financialfund.server.service.BaseFundService;

public class SplashActivity extends BaseSplashActivity {
    @Override
    protected void showDebug() {
        debugIsVisible = true;
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog_app_debug);
        d.setCancelable(false);
        String debugUrl = Preferences.with(this).debugInfo().url();
        String HasantdebugUrl = BaseFundService.DEBUG_URL(this);
        String debugPackageName = Preferences.with(this).debugInfo().packageName();
        String staticPackageName = ApplicationStaticParameter.PACKAGE_NAME;
        ((EditText) d.findViewById(R.id.txtUrl)).setText(!debugUrl.equalsIgnoreCase("") ? debugUrl :
                !staticPackageName.equalsIgnoreCase("") ? staticPackageName : RetrofitManager.BASE_URL);
        ((EditText) d.findViewById(R.id.txtHasanatUrl)).setText(!HasantdebugUrl.equalsIgnoreCase("") ? HasantdebugUrl : BaseFundService.FUND_URL);
        ((EditText) d.findViewById(R.id.txtpackageName)).setText(debugPackageName.equalsIgnoreCase("") ?
                BaseNtkApplication.get().getApplicationParameter().PACKAGE_NAME() : debugPackageName);
        ((EditText) d.findViewById(R.id.txtLinkSiteId)).setText(Preferences.with(this).UserInfo().siteId().toString());
        ((EditText) d.findViewById(R.id.txtlinkUserId)).setText(Preferences.with(this).UserInfo().linkUserId().toString());
        ((EditText) d.findViewById(R.id.txtLinkMemberId)).setText(Preferences.with(this).UserInfo().linkMemberId().toString());
        ((EditText) d.findViewById(R.id.txtDeviceId)).setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        ((EditText) d.findViewById(R.id.txtApplicationId)).setText(NTKApplication.get().getApplicationParameter().APPLICATION_ID());
        d.findViewById(R.id.debugReset).setOnClickListener(v -> {

            ApplicationStaticParameter.URL = "";
            ApplicationStaticParameter.PACKAGE_NAME = "";
            Preferences.with(this).debugInfo().setCount(0);
            Preferences.with(this).debugInfo().setUrl("");
            Preferences.with(this).debugInfo().setPackageName("");
            BaseFundService.SET_DEBUG_URL(this, "");
            d.dismiss();
            debugIsVisible = false;
            getTokenDevice();
        });
        d.findViewById(R.id.debugStart).setOnClickListener(v -> {
            ApplicationStaticParameter.URL = ((EditText) d.findViewById(R.id.txtUrl)).getText().toString();
            ApplicationStaticParameter.PACKAGE_NAME = ((EditText) d.findViewById(R.id.txtpackageName)).getText().toString();
            BaseFundService.SET_DEBUG_URL(this, ((EditText) d.findViewById(R.id.txtHasanatUrl)).getText().toString());
            Preferences.with(this).debugInfo().setCount(20);
            Preferences.with(this).debugInfo().setUrl(ApplicationStaticParameter.URL);
            Preferences.with(this).debugInfo().setPackageName(ApplicationStaticParameter.PACKAGE_NAME);
            d.dismiss();
            debugIsVisible = false;
            getTokenDevice();
        });

        d.show();
    }
}
