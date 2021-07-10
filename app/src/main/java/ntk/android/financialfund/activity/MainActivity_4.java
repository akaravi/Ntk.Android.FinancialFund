package ntk.android.financialfund.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

import ntk.android.base.adapter.BaseRecyclerAdapter;
import ntk.android.base.view.NViewUtils;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.MainViewPager3;
import ntk.android.financialfund.adapter.MainViewPager4;

public class MainActivity_4 extends BaseMainViewpagerActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDirectContentView(R.layout.main_activity_4);
        ViewPager mPager = findViewById(R.id.slpager);


            mPager.setAdapter(new MainViewPager4( this, createTab1(), createTab2()));
            mPager.getAdapter().notifyDataSetChanged();

        PageIndicatorView indicator = (PageIndicatorView)
                findViewById(R.id.pageIndicatorView);

        indicator.setViewPager(mPager);

        mPager.setCurrentItem(0, true);
    }
}
