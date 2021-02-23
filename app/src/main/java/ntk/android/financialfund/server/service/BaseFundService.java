package ntk.android.financialfund.server.service;

import android.content.Context;

import java.util.Map;

import ntk.android.base.config.ConfigRestHeader;
import ntk.android.base.config.RetrofitManager;

class BaseFundService{
    public static final String FUND_URL = "https://e-hasanat.ir/";
    protected final String baseUrl = "api/v1/";
    protected final String controlerUrl;
    protected Context context;
    protected Map<String, String> headers;

    public BaseFundService(Context context, String controlerUrl) {
        this.controlerUrl = controlerUrl;
        this.context = context;
        headers = new ConfigRestHeader().GetHeaders(context);

    }

    public <K> K getRetrofit(Class<K> kClass) {
        K iCmsApiServerBase = new RetrofitManager(context, FUND_URL).getRetrofitUnCached().create(kClass);
        return iCmsApiServerBase;
    }

}
