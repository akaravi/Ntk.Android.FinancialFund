package ntk.android.financial.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import ntk.android.base.activity.BaseActivity;
import ntk.android.financial.R;

public class Class5 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calss5);
        ((Button) findViewById(R.id.btnOk)).setText("گزارش");
    }
}
