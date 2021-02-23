package ntk.android.financialfund.db;

import android.content.Context;

import ntk.android.base.utill.prefrense.EasyPreference;

public class FoundInfo {
    Context c;

    public FoundInfo(Context context) {
        this.c = context;
    }

    public void setToken(String b) {
        EasyPreference.with(c).addString("Found_Token", b);
    }

    public String getToken() {
        return EasyPreference.with(c).getString("Found_Token", "");
    }
}
