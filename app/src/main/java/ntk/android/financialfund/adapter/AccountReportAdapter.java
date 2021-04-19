package ntk.android.financialfund.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ntk.android.base.adapter.BaseRecyclerAdapter;
import ntk.android.financialfund.R;
import ntk.android.financialfund.server.model.FundAccountReport;

public class AccountReportAdapter extends BaseRecyclerAdapter<FundAccountReport, AccountReportAdapter.VH> {
    public AccountReportAdapter(List<FundAccountReport> list) {
        super(list);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(inflate(parent, R.layout.row_account_report));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    public class VH extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView debit;
        TextView order;
        TextView ws;

        public VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            date = itemView.findViewById(R.id.title_text);
        }
    }
}
