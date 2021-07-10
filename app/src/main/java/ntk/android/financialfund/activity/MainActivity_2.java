package ntk.android.financialfund.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.MainViewPager2;
import ntk.android.financialfund.model.PanelViewModel;

public class MainActivity_2 extends BaseMainViewpagerActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDirectContentView(R.layout.main_activity_2);
        ViewPager mPager = findViewById(R.id.slpager);

        try {
            mPager.setAdapter(new MainViewPager2(this, createTab1(), createTab2()));
            mPager.getAdapter().notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageIndicatorView indicator = (PageIndicatorView)
                findViewById(R.id.pageIndicatorView);

        indicator.setViewPager(mPager);

        mPager.setCurrentItem(0, true);
    }

}
