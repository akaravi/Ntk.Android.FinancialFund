package ntk.android.financialfund;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import es.dmoral.toasty.Toasty;
import ntk.android.base.ApplicationParameter;
import ntk.android.base.ApplicationStaticParameter;
import ntk.android.base.ApplicationStyle;
import ntk.android.base.NTKApplication;
import ntk.android.base.utill.FontManager;
import ntk.android.base.view.ViewController;
import ntk.android.financialfund.activity.MainActivity_1;
//import ntk.android.base.view.ViewController;


public class MyApplication extends NTKApplication {

    public static boolean Inbox = false;

    @Override
    public void onCreate() {
        applicationStyle =new MyAppStyle();
        super.onCreate();
        DEBUG = true;
        if (!new File(getCacheDir(), "image").exists()) {
            new File(getCacheDir(), "image").mkdirs();
        }
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(new File(getCacheDir(), "image")))
                .diskCacheFileNameGenerator(imageUri -> {
                    String[] Url = imageUri.split("/");
                    return Url[Url.length];
                })
                .build();
        ImageLoader.getInstance().init(config);

        Toasty.Config.getInstance()
                .setToastTypeface(FontManager.T1_Typeface(getApplicationContext()))
                .setTextSize(14).apply();

    }

    @Override
    protected void attachBaseContext(Context base) {
        instance = this;
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    protected ApplicationStaticParameter getConfig() {
        ApplicationStaticParameter applicationStaticParameter = new ApplicationStaticParameter();
//        ApplicationStaticParameter.URL = "http://52d21137cc37.ngrok.io";
//        ApplicationStaticParameter.PACKAGE_NAME = "ntk.android.financialfund.App88";
        return applicationStaticParameter;
    }

    @Override
    public ApplicationParameter getApplicationParameter() {
        return new ApplicationParameter(BuildConfig.APPLICATION_ID, BuildConfig.APPLICATION_ID, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE);
    }
}
