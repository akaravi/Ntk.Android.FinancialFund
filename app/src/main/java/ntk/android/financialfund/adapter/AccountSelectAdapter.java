package ntk.android.financialfund.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ntk.android.financialfund.R;
import ntk.android.financialfund.server.model.FundBranchAccount;

public class AccountSelectAdapter extends ArrayAdapter<FundBranchAccount> {

    private final List<FundBranchAccount> list;

    public AccountSelectAdapter(@NonNull Context context, List<FundBranchAccount> arrayList) {
        super(context, R.layout.row_account_bank, arrayList);
        list = arrayList;
    }

    @Override
    public FundBranchAccount getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_account_bank, parent, false);
        }
        ((TextView) view.findViewById(R.id.textView)).setText(getItem(position).accountKey + "شماره حساب :");
        ((TextView) view.findViewById(R.id.textView1)).setText(getItem(position).accountClientDescription );
        return view;
    }
}