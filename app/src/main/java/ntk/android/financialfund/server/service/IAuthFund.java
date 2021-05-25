package ntk.android.financialfund.server.service;

import java.util.Map;

import io.reactivex.Observable;
import ntk.android.base.entitymodel.base.CaptchaModel;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.base.entitymodel.base.ErrorExceptionBase;
import ntk.android.financialfund.server.model.ClientTokenModel;
import ntk.android.financialfund.server.model.FundCaptchaModel;
import ntk.android.financialfund.server.model.GetTokenRequest;
import ntk.android.financialfund.server.model.OrderTokenRequestModel;
import ntk.android.financialfund.server.model.OrderUserToken;
import ntk.android.financialfund.server.model.UserToken;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

interface IAuthFund {
    @GET("api/v1/auth/captcha")
    @Headers({"content-type: application/json"})
    Observable<ErrorException<FundCaptchaModel>> AuthCaptcha(@HeaderMap Map<String, String> headers);

    @GET("api/v1/auth/captcha")
    @Headers({"content-type: application/json"})
    Observable<ErrorException<CaptchaModel>> Captcha();

    @POST("/api/v1/Auth/OrderToken")
    @Headers({"content-type: application/json"})
    Observable<ErrorException<OrderUserToken>> OrderToken(@HeaderMap Map<String, String> headers, @Body OrderTokenRequestModel body);

    @POST("/api/v1/Auth/GetToken")
    @Headers({"content-type: application/json"})
    Observable<ErrorException<ClientTokenModel >> GetToken(@HeaderMap Map<String, String> headers, @Body UserToken body);

    @GET("/api/v1/Auth/CheckToken")
    @Headers({"content-type: application/json"})
    Observable<ErrorException<ClientTokenModel>> CheckToken(@HeaderMap Map<String, String> headers);

}
