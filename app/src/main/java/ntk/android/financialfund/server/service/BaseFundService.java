package ntk.android.financialfund.server.service;

import android.content.Context;

import java.util.Map;

import ntk.android.base.config.RetrofitManager;
import ntk.android.base.utill.prefrense.EasyPreference;
import ntk.android.base.utill.prefrense.Preferences;

public class BaseFundService {
    public static String FUND_URL = "https://e-hasanat.ir/";
//        public static  String FUND_URL = "http://308bbd281096.ngrok.io";
    protected final String baseUrl = "api/v1/";
    protected final String controlerUrl;
    protected Context context;
    protected Map<String, String> headers;//7235273

    public BaseFundService(Context context, String controlerUrl) {
        this.controlerUrl = controlerUrl;
        this.context = context;
        headers = new ConfigFundsHeaders().GetHeaders(context);
        int ntk_url_count = Preferences.with(context).debugInfo().count();
        if (ntk_url_count > 0) {
            FUND_URL = DEBUG_URL(context);
        }
    }

    public static String DEBUG_URL(Context context) {
        return EasyPreference.with(context).getString("HASANAT_TEST_URL", "");
    }

    public static void SET_DEBUG_URL(Context context, String str) {
        EasyPreference.with(context).addString("HASANAT_TEST_URL", str);
    }

    public <K> K getRetrofit(Class<K> kClass) {
        K iCmsApiServerBase = new RetrofitManager(context, FUND_URL,true).getRetrofitUnCached().create(kClass);
        return iCmsApiServerBase;
    }

}
