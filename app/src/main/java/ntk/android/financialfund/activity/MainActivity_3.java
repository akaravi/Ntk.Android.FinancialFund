package ntk.android.financialfund.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

import ntk.android.base.adapter.BaseRecyclerAdapter;
import ntk.android.base.view.NViewUtils;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.MainViewPager3;

public class MainActivity_3 extends BaseMainViewpagerActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDirectContentView(R.layout.main_activity_3);
        ViewPager mPager = findViewById(R.id.slpager);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById(R.id.MainLogo).getLayoutParams();
        layoutParams.topMargin=findViewById(R.id.main3TopView).getLayoutParams().height/2;
        findViewById(R.id.MainLogo).setLayoutParams(layoutParams);
        try {
            int totalHeight = BaseRecyclerAdapter.getScreenHeight()
                    - findViewById(R.id.main3TopView).getLayoutParams().height
                    -( findViewById(R.id.main3TopView).getLayoutParams().height)/2
                    - findViewById(R.id.lin).getLayoutParams().height
                    - findViewById(R.id.main3TopView).getLayoutParams().height
                    - findViewById(R.id.pageIndicatorView).getLayoutParams().height - NViewUtils.dpToPx(this, 120);
            mPager.setAdapter(new MainViewPager3(totalHeight, this, createTab1(), createTab2()));
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
