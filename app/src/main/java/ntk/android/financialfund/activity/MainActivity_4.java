package ntk.android.financialfund.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.MainAdapter4;
import ntk.android.financialfund.model.PanelViewModel;

public class MainActivity_4 extends BaseMainViewpagerActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDirectContentView(R.layout.main_activity_4);

        RecyclerView rc = findViewById(R.id.rv_parent);
        rc.setLayoutManager(new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL, false));
        List<PanelViewModel> allButtons=new ArrayList<>();
        allButtons.addAll(createTab1());
        allButtons.addAll(createTab2());
        rc.setAdapter(new MainAdapter4(allButtons,this));
    }
}
