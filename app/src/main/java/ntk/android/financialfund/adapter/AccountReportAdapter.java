package ntk.android.financialfund.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ntk.android.base.adapter.BaseRecyclerAdapter;
import ntk.android.base.utill.FontManager;
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
        FundAccountReport item = getItem(position);
        holder.title.setText(item.Title);
        holder.desc.setText(item.Description);
        holder.date.setText(item.PersianActionDate);
        holder.debit.setText(String.format("%.0f", item.Debtor) );
        holder.credit.setText(String.format("%.0f",item.Creditor) );
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView date;
        TextView debit;
        TextView credit;

        public VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            setTypeface(itemView.getContext(), title);
            {
                date = itemView.findViewById(R.id.it1).findViewById(R.id.txtDesc);
                TextView tv = itemView.findViewById(R.id.it1).findViewById(R.id.txtTv);
                tv.setText("تاریخ:");
                setTypeface(itemView.getContext(), date, tv);
                ((ImageView) itemView.findViewById(R.id.it1).findViewById(R.id.icon)).setImageResource(R.drawable.date_icon);
            }
            {
                desc = itemView.findViewById(R.id.it2).findViewById(R.id.txtDesc);
                TextView tv = itemView.findViewById(R.id.it2).findViewById(R.id.txtTv);
                tv.setText("توضیح:");
                setTypeface(itemView.getContext(), desc, tv);
                ((ImageView) itemView.findViewById(R.id.it2).findViewById(R.id.icon)).setImageResource(R.drawable.description);
            }

            {
                debit = itemView.findViewById(R.id.it3).findViewById(R.id.txtDesc);
                TextView tv = itemView.findViewById(R.id.it3).findViewById(R.id.txtTv);
                tv.setText("بستانکار:");
                setTypeface(itemView.getContext(), debit, tv);
                ((ImageView) itemView.findViewById(R.id.it3).findViewById(R.id.icon)).setImageResource(R.drawable.debit);
            }
            {
                credit = itemView.findViewById(R.id.it4).findViewById(R.id.txtDesc);
                TextView tv = itemView.findViewById(R.id.it4).findViewById(R.id.txtTv);
                tv.setText("بدهکار:");
                setTypeface(itemView.getContext(), credit, tv);
                ((ImageView) itemView.findViewById(R.id.it4).findViewById(R.id.icon)).setImageResource(R.drawable.debit);
            }

        }

        private void setTypeface(Context c, TextView... tvs) {
            for (TextView t : tvs) {
                t.setTypeface(FontManager.T1_Typeface(c));
            }
        }
    }
}
