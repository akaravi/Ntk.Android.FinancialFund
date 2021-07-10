package ntk.android.financialfund.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import ntk.android.base.adapter.BaseRecyclerAdapter;
import ntk.android.base.utill.FontManager;
import ntk.android.base.view.NViewUtils;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.MainViewPager3;
import ntk.android.financialfund.adapter.drawer.DrawerAdapter;
import ntk.android.financialfund.model.PanelViewModel;

public class MainActivity_3 extends BaseMainViewpagerActivity {
    FlowingDrawer drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDirectContentView(R.layout.main_activity_3);
        ViewPager mPager = findViewById(R.id.slpager);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById(R.id.MainLogo).getLayoutParams();
        layoutParams.topMargin = findViewById(R.id.main3TopView).getLayoutParams().height / 2;
        findViewById(R.id.MainLogo).setLayoutParams(layoutParams);
        try {
            int totalHeight = BaseRecyclerAdapter.getScreenHeight()
                    - findViewById(R.id.main3TopView).getLayoutParams().height
                    - (findViewById(R.id.main3TopView).getLayoutParams().height) / 2
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

        //menu click listener
        findViewById(R.id.menuImage).setOnClickListener(view -> drawer.openMenu(true));
        //set drawer
        drawer = findViewById(R.id.drawerlayout);
        drawer.requestDisallowInterceptTouchEvent(true);
        RecyclerView RvDrawer = findViewById(R.id.RecyclerDrawer);
        RvDrawer.setHasFixedSize(true);
        RvDrawer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DrawerAdapter AdDrawer = new DrawerAdapter(getdrawerList(), drawer, this);
        RvDrawer.setAdapter(AdDrawer);

        //set font
        setFont();
    }

    private void setFont() {
        ((TextView) findViewById(R.id.textview1)).setTypeface(FontManager.T1_BOLD_Typeface(this));
    }


    private List<PanelViewModel> getdrawerList() {
        List<PanelViewModel> m = new ArrayList<>();
        m.add(new PanelViewModel().setTag(7).setTitle("صندوق پیام").setImageId(R.drawable.search));
        m.add(new PanelViewModel().setTag(8).setTitle("پشتیبانی").setImageId(R.drawable.support));
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


}
