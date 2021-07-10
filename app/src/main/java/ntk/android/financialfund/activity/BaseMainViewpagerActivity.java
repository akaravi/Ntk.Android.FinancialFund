package ntk.android.financialfund.activity;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import ntk.android.base.activity.abstraction.AbstractMainActivity;
import ntk.android.base.activity.common.NotificationsActivity;
import ntk.android.base.activity.poling.PolingDetailActivity;
import ntk.android.base.activity.ticketing.FaqActivity;
import ntk.android.base.activity.ticketing.TicketListActivity;
import ntk.android.base.activity.ticketing.TicketSearchActivity;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.PanelInterface;
import ntk.android.financialfund.dialog.CheckTokenDialog;
import ntk.android.financialfund.model.PanelViewModel;

abstract class BaseMainViewpagerActivity extends AbstractMainActivity implements PanelInterface {
    List<PanelViewModel> createTab1() {
        List<PanelViewModel> m = new ArrayList<>();
        m.add(new PanelViewModel().setTag(0).setTitle(getString(R.string.accountToaccount)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTag(1).setTitle(getString(R.string.mainCard1)).setImageId(R.drawable.support));
        m.add(new PanelViewModel().setTag(2).setTitle(getString(R.string.mainCard4)).setImageId(R.drawable.question));

        m.add(new PanelViewModel().setTag(3).setTitle(getString(R.string.mainCard3)).setImageId(R.drawable.question));
        m.add(new PanelViewModel().setTag(4).setTitle(getString(R.string.mainCard6)).setImageId(R.drawable.question));
        m.add(new PanelViewModel().setTag(5).setTitle(getString(R.string.mainCard5)).setImageId(R.drawable.question));

        m.add(new PanelViewModel().setTag(6).setTitle(getString(R.string.mainCard7)).setImageId(R.drawable.question));
        m.add(new PanelViewModel().setTag(7).setTitle("صندوق پیام").setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTag(8).setTitle("پشتیبانی").setImageId(R.drawable.support));

        return m;
    }

    List<PanelViewModel> createTab2() {
        List<PanelViewModel> m = new ArrayList<>();
        m.add(new PanelViewModel().setTag(9).setTitle("پیگیری").setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTag(10).setTitle("پرسش های متداول").setImageId(R.drawable.question));
        m.add(new PanelViewModel().setTag(11).setTitle("بازخورد").setImageId(R.drawable.feed_back));

        m.add(new PanelViewModel().setTag(12).setTitle("اخبار").setImageId(R.drawable.news));
        m.add(new PanelViewModel().setTag(13).setTitle("راهنما").setImageId(R.drawable.intro));
        m.add(new PanelViewModel().setTag(14).setTitle("مجلات").setImageId(R.drawable.pooling));

        m.add(new PanelViewModel().setTag(15).setTitle("نظرسنجی").setImageId(R.drawable.pooling));
        m.add(new PanelViewModel().setTag(16).setTitle("درباره ما").setImageId(R.drawable.about_us));
        m.add(new PanelViewModel().setTag(17).setTitle("دعوت از دوستان").setImageId(R.drawable.invate));

        return m;
    }

    @Override
    public void OnCardClick(PanelViewModel panel) {
        Intent i = new Intent(this, CheckTokenDialog.class);
        switch (panel.tag) {
            case 0:
                i.putExtra(CheckTokenDialog.EXTRA_CLASSNAME, AccountToAccountActivity.class);
                break;

            case 1:
                i = new Intent(this, Class1.class);
                break;

            case 2:
                i.putExtra(CheckTokenDialog.EXTRA_CLASSNAME, AccountToLoanActivity.class);
                break;

            case 3:
                i = new Intent(this, Class3.class);
                break;

            case 4:
                i.putExtra(CheckTokenDialog.EXTRA_CLASSNAME, LoanReportActivity.class);
                break;

            case 5:
                i = new Intent(this, Class5.class);
                break;

            case 6:
                i.putExtra(CheckTokenDialog.EXTRA_CLASSNAME, AccountReportActivity.class);
                break;

            case 7:
                i = new Intent(this, NotificationsActivity.class);
                break;

            case 8:
                i = new Intent(this, TicketListActivity.class);
                break;

            case 9:
                i = new Intent(this, TicketSearchActivity.class);
                break;

            case 10:
                i = new Intent(this, FaqActivity.class);
                break;

            case 11: {
                onFeedbackClick();
                return;
            }

            case 12:
                i = new Intent(this, NewsListActivity.class);
                break;

            case 13: {
                onMainIntro();
                return;
            }

            case 14:
                i = new Intent(this, ArticleListActivity.class);
                break;

            case 15:
                i = new Intent(this, PolingDetailActivity.class);
                break;

            case 16:
                i = new Intent(this, AboutUsActivity.class);
                break;

            case 17: {
                onInviteMethod();
                return;
            }
        }
        startActivity(i);
    }
}
