package ntk.android.financialfund.server.service;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import ntk.android.base.config.ListOfJson;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.financialfund.server.model.AccountToAccountModel;
import ntk.android.financialfund.server.model.FundBranchAccount;

public class AccountFundsService extends BaseFundService {
    Class<FundBranchAccount> teClass = FundBranchAccount.class;

    public AccountFundsService(Context context) {
        super(context, "Account");
    }

    public Observable<ErrorException<FundBranchAccount>> getOne(Long Id) {
        BehaviorSubject<ErrorException<FundBranchAccount>> mMovieCache = BehaviorSubject.create();
        Observable<ErrorException<FundBranchAccount>> getone = getRetrofit(IAccountFund.class).GetOne(baseUrl + controlerUrl + "/" + Id, headers);
        getone.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ErrorException>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ErrorException o) {
                ErrorException a = new ErrorException();
                Gson gson = new GsonBuilder()
                        .enableComplexMapKeySerialization()
                        .setDateFormat("yyyy-MM-dd'T'hh:mm:ss").serializeNulls()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                        .create();
                o.Item = gson.fromJson(gson.toJson(o.Item), teClass);
                o.ListItems = gson.fromJson(gson.toJson(o.ListItems), new ListOfJson<FundBranchAccount>(teClass));
                mMovieCache.onNext(o);
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

    public Observable<ErrorException<FundBranchAccount>> getList() {
        BehaviorSubject<ErrorException<FundBranchAccount>> mMovieCache = BehaviorSubject.create();
        Observable<ErrorException<FundBranchAccount>> getone = getRetrofit(IAccountFund.class).GetList(baseUrl + controlerUrl + "/", headers);
        getone.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ErrorException>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ErrorException o) {
                ErrorException a = new ErrorException();
                Gson gson = new GsonBuilder()
                        .enableComplexMapKeySerialization()
                        .setDateFormat("yyyy-MM-dd'T'hh:mm:ss").serializeNulls()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                        .create();
                o.Item = gson.fromJson(gson.toJson(o.Item), teClass);
                o.ListItems = gson.fromJson(gson.toJson(o.ListItems), new ListOfJson<FundBranchAccount>(teClass));
                mMovieCache.onNext(o);
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

    public Observable<ErrorException<FundBranchAccount>> accountToAccount(AccountToAccountModel req) {
        BehaviorSubject<ErrorException<FundBranchAccount>> mMovieCache = BehaviorSubject.create();
        Observable<ErrorException<FundBranchAccount>> getone = getRetrofit(IAccountFund.class).AccountToAccount(baseUrl + controlerUrl + "/AccountToAccount", headers, req);
        getone.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ErrorException>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ErrorException o) {
                ErrorException a = new ErrorException();
                Gson gson = new GsonBuilder()
                        .enableComplexMapKeySerialization()
                        .setDateFormat("yyyy-MM-dd'T'hh:mm:ss").serializeNulls()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                        .create();
                o.Item = gson.fromJson(gson.toJson(o.Item), teClass);
                o.ListItems = gson.fromJson(gson.toJson(o.ListItems), new ListOfJson<FundBranchAccount>(teClass));
                mMovieCache.onNext(o);
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
