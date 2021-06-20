package ntk.android.financialfund.activity;

import java.util.ArrayList;
import java.util.List;

import ntk.android.base.activity.abstraction.AbstractMainActivity;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.PanelInterface;
import ntk.android.financialfund.model.PanelViewModel;

abstract class BaseMainViewpagerActivity extends AbstractMainActivity implements PanelInterface {
     List<PanelViewModel> createTab2() {
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

     List<PanelViewModel> createTab1() {
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
