package ntk.android.financial.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ntk.android.base.activity.BaseActivity;
import ntk.android.financial.R;

public class Class4 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class4);
        ((TextView) findViewById(R.id.txtToolbar)).setText(getString(R.string.mainCard4));
    }
}
