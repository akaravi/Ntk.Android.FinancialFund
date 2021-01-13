package ntk.android.financialfund.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ntk.android.base.activity.BaseActivity;
import ntk.android.financialfund.R;

public class Class2 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class2);
        ((TextView) findViewById(R.id.txtToolbar)).setText(getString(R.string.mainCard2));
    }
}
