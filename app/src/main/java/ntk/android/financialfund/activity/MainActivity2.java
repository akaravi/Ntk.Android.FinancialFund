package ntk.android.financialfund.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import ntk.android.base.activity.abstraction.AbstractMainActivity;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.PanelInterface;
import ntk.android.financialfund.adapter.PanelSlidingAdapter;
import ntk.android.financialfund.model.PanelViewModel;

public class MainActivity2 extends AbstractMainActivity implements PanelInterface {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDirectContentView(R.layout.main_activity_2);
        ViewPager mPager = findViewById(R.id.slpager);

        try {
            mPager.setAdapter(new PanelSlidingAdapter(this, createTab1(), createTab2()));
            mPager.getAdapter().notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageIndicatorView indicator = (PageIndicatorView)
                findViewById(R.id.pageIndicatorView);

        indicator.setViewPager(mPager);

        mPager.setCurrentItem(0, true);
    }

    private List<PanelViewModel> createTab2() {
        List<PanelViewModel> m = new ArrayList<>();
        m.add(new PanelViewModel().setTitle(getString(R.string.accountToaccount)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard1)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard4)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard4)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard3)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard6)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard5)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard7)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle("پشتیبانی").setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle("صندوق پیام").setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle("پیگیری").setImageId(R.drawable.search));
        return m;
    }

    private List<PanelViewModel> createTab1() {
        List<PanelViewModel> m = new ArrayList<>();
        m.add(new PanelViewModel().setTitle(getString(R.string.accountToaccount)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard1)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard4)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard4)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard3)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard6)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard5)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle(getString(R.string.mainCard7)).setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle("پشتیبانی").setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle("صندوق پیام").setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTitle("پیگیری").setImageId(R.drawable.search));
        return m;
    }

    @Override
    public void OnCardClick(PanelViewModel panel) {

    }
}
