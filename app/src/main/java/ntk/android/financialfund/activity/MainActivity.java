package ntk.android.financialfund.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ntk.android.base.activity.abstraction.AbstractMainActivity;
import ntk.android.base.activity.common.NotificationsActivity;
import ntk.android.base.activity.poling.PolingDetailActivity;
import ntk.android.base.activity.ticketing.FaqActivity;
import ntk.android.base.activity.ticketing.TicketListActivity;
import ntk.android.base.activity.ticketing.TicketSearchActivity;
import ntk.android.base.adapter.BaseRecyclerAdapter;
import ntk.android.base.config.NtkObserver;
import ntk.android.base.entitymodel.base.ErrorException;
import ntk.android.base.entitymodel.base.FilterModel;
import ntk.android.base.entitymodel.news.NewsContentModel;
import ntk.android.base.services.news.NewsContentService;
import ntk.android.base.utill.FontManager;
import ntk.android.base.utill.prefrense.Preferences;
import ntk.android.base.view.NViewUtils;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.CoreImageAdapter;
import ntk.android.financialfund.dialog.CheckTokenDialog;
import ntk.android.financialfund.event.toolbar.EVSearchClick;

public class MainActivity extends AbstractMainActivity {

    @BindViews({
            R.id.txtCard1,
            R.id.txtCard2,
            R.id.txtCard3,
            R.id.txtCard4,
            R.id.txtCard5,
            R.id.txtCard6,
            R.id.txtCard7,
            R.id.news,
            R.id.pooling,
            R.id.invite,
            R.id.feedback,
            R.id.question,
            R.id.intro,
            R.id.blog,
            R.id.aboutUs,
            R.id.support,
            R.id.message,
            R.id.search})
    List<TextView> lbl;

    @BindViews({R.id.mainCard1,
            R.id.mainCard2,
            R.id.mainCard3,
            R.id.mainCard4,
            R.id.mainCard5,
            R.id.mainCard6,
            R.id.mainCard7,
            R.id.newsBtn,
            R.id.poolingBtn,
            R.id.searchBtn,
            R.id.inviteBtn,
            R.id.feedbackBtn,
            R.id.questionBtn,
            R.id.introBtn,
            R.id.blogBtn,
            R.id.aboutUsBtn,
            R.id.supportBtn,
            R.id.messageBtn})
    List<LinearLayout> btn;

//    @BindView(R.id.bannerLayout)
//    LinearLayout layout;

    @BindView(R.id.SliderActMain)
    RecyclerView Slider;

//    @BindView(R.id.RefreshMain)
//    SwipeRefreshLayout Refresh;

    private long lastPressedTime;
    private static final int PERIOD = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDirectContentView(R.layout.main_activity);
        fixViewSize();
        ButterKnife.bind(this);
        init();
//        Preferences.with(MainActivity.this).UserInfo().setMobile("09132131542");
    }

    private void fixViewSize() {
        int screenHeight = BaseRecyclerAdapter.getScreenHeight();
        findViewById(R.id.linear).getLayoutParams().height = (int) (screenHeight * .35);
        int minimumHeight = NViewUtils.dpToPx(this, 640);
        if (screenHeight >= minimumHeight)
            findViewById(R.id.panelButtons).getLayoutParams().height = (int) (screenHeight);
        else
            findViewById(R.id.panelButtons).getLayoutParams().height = (int) (minimumHeight);
        if (screenHeight >= minimumHeight)
            findViewById(R.id.mainRv).getLayoutParams().height = (screenHeight + (int) (screenHeight * .35));
        else
            findViewById(R.id.mainRv).getLayoutParams().height = (minimumHeight + (int) (screenHeight * .35));
    }

    private void init() {
        setAnimation();
        for (int i = 0; i < lbl.size(); i++) {
            lbl.get(i).setTypeface(FontManager.T2_Typeface(this));
        }
        HandelSlider();
    }

    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        for (int i = 0; i < btn.size(); i++) {
            btn.get(i).startAnimation(scaleAnimation);
        }
        findViewById(R.id.linear).startAnimation(alphaAnimation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void EvClickSearch(EVSearchClick click) {
        startActivity(new Intent(this, TicketSearchActivity.class));
    }


    private void HandelSlider() {

        FilterModel request = new FilterModel();
        request.RowPerPage = 5;
        request.CurrentPageNumber = 1;
        new NewsContentService(this).getAll(request).
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new NtkObserver<ErrorException<NewsContentModel>>() {

                    @Override
                    public void onNext(ErrorException<NewsContentModel> newsContentResponse) {
                        if (newsContentResponse.IsSuccess) {
                            if (newsContentResponse.ListItems.size() > 0) {
                                findViewById(R.id.linear).setVisibility(View.VISIBLE);
                                findViewById(R.id.linear).setBackground(null);
                                SnapHelper snapHelper = new PagerSnapHelper();
                                CoreImageAdapter adapter = new CoreImageAdapter(MainActivity.this, newsContentResponse.ListItems);
                                Slider.setHasFixedSize(true);
                                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
                                Slider.setLayoutManager(manager);
                                Slider.setAdapter(adapter);
                                snapHelper.attachToRecyclerView(Slider);
                                adapter.notifyDataSetChanged();
                            } else {
                                findViewById(R.id.linear).setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    @OnClick(R.id.mainCard1)
    public void oncard1() {
        this.startActivity(new Intent(this, Class1.class));
    }

    @OnClick(R.id.mainCard2)
    public void oncard2() {
        Intent i = new Intent(this, CheckTokenDialog.class);
        i.putExtra(CheckTokenDialog.EXTRA_CLASSNAME, AccountToAccountActivity.class);
        this.startActivity(i);
    }

    @OnClick(R.id.mainCard3)
    public void oncard3() {
        this.startActivity(new Intent(this, Class3.class));
    }

    @OnClick(R.id.mainCard4)
    public void oncard4() {
        Intent i = new Intent(this, CheckTokenDialog.class);
        i.putExtra(CheckTokenDialog.EXTRA_CLASSNAME, AccountToLoanActivity.class);
        this.startActivity(i);
    }

    @OnClick(R.id.mainCard5)
    public void oncard5() {
        this.startActivity(new Intent(this, Class5.class));
    }

    @OnClick(R.id.mainCard6)
    public void oncard6() {
        Intent i = new Intent(this, CheckTokenDialog.class);
        i.putExtra(CheckTokenDialog.EXTRA_CLASSNAME, LoanReportActivity.class);
        this.startActivity(i);
    }

    @OnClick(R.id.mainCard7)
    public void oncard7() {
        Intent i = new Intent(this, CheckTokenDialog.class);
        i.putExtra(CheckTokenDialog.EXTRA_CLASSNAME, AccountReportActivity.class);
        this.startActivity(i);
    }

    @OnClick(R.id.supportBtn)
    public void onSupportClick() {
        this.startActivity(new Intent(this, TicketListActivity.class));
    }

    @OnClick(R.id.searchBtn)
    public void onSearchClick() {
        this.startActivity(new Intent(this, TicketSearchActivity.class));
    }

    @OnClick(R.id.messageBtn)
    public void onInboxClick() {
        this.startActivity(new Intent(this, NotificationsActivity.class));
    }

    @OnClick(R.id.newsBtn)
    public void onNewsClick() {
        this.startActivity(new Intent(this, NewsListActivity.class));
    }

    @OnClick(R.id.feedbackBtn)
    public void onFeedBackClick() {
        onFeedbackClick();
    }

    @OnClick(R.id.poolingBtn)
    public void onPoolingClick() {
        this.startActivity(new Intent(this, PolingDetailActivity.class));
    }

    @OnClick(R.id.inviteBtn)
    public void onInviteClick() {
        onInviteMethod();
    }

    @OnClick(R.id.questionBtn)
    public void onQuestionClick() {
        this.startActivity(new Intent(this, FaqActivity.class));
    }

    @OnClick(R.id.blogBtn)
    public void onBlogClick() {
        this.startActivity(new Intent(this, ArticleListActivity.class));
    }

    @OnClick(R.id.aboutUsBtn)
    public void onAboutUsClick() {
        this.startActivity(new Intent(this, AboutUsActivity.class));
    }

    @OnClick(R.id.introBtn)
    public void onIntroClick() {
        onMainIntro();
    }
}