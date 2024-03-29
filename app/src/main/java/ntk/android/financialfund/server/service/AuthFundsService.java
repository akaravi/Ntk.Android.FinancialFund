package ntk.android.financialfund.server.service;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import ntk.android.base.entitymodel.base.CaptchaModel;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.financialfund.server.model.ClientTokenModel;
import ntk.android.financialfund.server.model.OrderTokenRequestModel;
import ntk.android.financialfund.server.model.OrderUserToken;
import ntk.android.financialfund.server.model.UserToken;

public class AuthFundsService extends BaseFundService {
    public AuthFundsService(Context context) {
        super(context, "Auth");
    }


    public Observable<ErrorException<CaptchaModel>> getCaptcha() {
        BehaviorSubject<ErrorException<CaptchaModel>> mMovieCache = BehaviorSubject.create();
        getRetrofit(IAuthFund.class).Captcha()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ErrorException<CaptchaModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ErrorException<CaptchaModel> model) {
                mMovieCache.onNext(model);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mMovieCache.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
        return mMovieCache;
    }

    public Observable<ErrorException<OrderUserToken>> orderToken(OrderTokenRequestModel req) {
        BehaviorSubject<ErrorException<OrderUserToken>> mMovieCache = BehaviorSubject.create();
        getRetrofit(IAuthFund.class).OrderToken(headers, req)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ErrorException<OrderUserToken>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ErrorException<OrderUserToken> model) {
                mMovieCache.onNext(model);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mMovieCache.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
        return mMovieCache;
    }

    public Observable<ErrorException<ClientTokenModel>> checkToken() {
        BehaviorSubject<ErrorException<ClientTokenModel>> mMovieCache = BehaviorSubject.create();
        getRetrofit(IAuthFund.class).CheckToken(headers)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ErrorException<ClientTokenModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ErrorException<ClientTokenModel> model) {
                if (!model.IsSuccess)
                    ConfigFundsHeaders.SET_TOKEN("");
                mMovieCache.onNext(model);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mMovieCache.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
        return mMovieCache;
    }

    public Observable<ErrorException<ClientTokenModel>> getToken(UserToken req) {
        BehaviorSubject<ErrorException<ClientTokenModel>> mMovieCache = BehaviorSubject.create();
        getRetrofit(IAuthFund.class).GetToken(headers, req)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ErrorException<ClientTokenModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ErrorException<ClientTokenModel> model) {
                if (model.IsSuccess)
                    ConfigFundsHeaders.SET_TOKEN(model.Item.token);
                mMovieCache.onNext(model);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mMovieCache.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
        return mMovieCache;
    }
}
