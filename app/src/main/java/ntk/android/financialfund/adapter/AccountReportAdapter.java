package ntk.android.financialfund.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
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
        if (item.Debtor == 0 && item.Creditor == 0)
            holder.actionLinear.setVisibility(View.GONE);
        else
            holder.actionLinear.setVisibility(View.VISIBLE);
        if (item.Debtor != 0) {
            holder.action.setText(digitSeparator(item.Debtor) + " ریال ");
            holder.actionTitle.setText("واریز : ");
        } else if (item.Creditor != 0) {
            holder.action.setText(digitSeparator(item.Creditor) + " ریال ");
            holder.actionTitle.setText("برداشت : ");
        }
        holder.remian.setText(digitSeparator(item.ActualRemain) + " ریال ");
    }

    public String digitSeparator(double t) {
        return (new DecimalFormat("###,###,###,###,###,###").format(t));
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView date;
        TextView action;
        TextView actionTitle;
        TextView remian;
        LinearLayout actionLinear;

        public VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            setTypeface(itemView.getContext(), title);
            {
                date = itemView.findViewById(R.id.it1).findViewById(R.id.txtDesc);
                TextView tv = itemView.findViewById(R.id.it1).findViewById(R.id.txtTv);
                tv.setText("تاریخ :");
                setTypeface(itemView.getContext(), date, tv);
                ((ImageView) itemView.findViewById(R.id.it1).findViewById(R.id.icon)).setImageResource(R.drawable.date_icon);
            }
            {
                desc = itemView.findViewById(R.id.it2).findViewById(R.id.txtDesc);
                TextView tv = itemView.findViewById(R.id.it2).findViewById(R.id.txtTv);
                tv.setText("توضیح :");
                setTypeface(itemView.getContext(), desc, tv);
                ((ImageView) itemView.findViewById(R.id.it2).findViewById(R.id.icon)).setImageResource(R.drawable.description);
            }

            {
                actionLinear = itemView.findViewById(R.id.it3);
                action = itemView.findViewById(R.id.it3).findViewById(R.id.txtDesc);
                actionTitle = itemView.findViewById(R.id.it3).findViewById(R.id.txtTv);
                actionTitle.setText("واریز :");
                setTypeface(itemView.getContext(), action, actionTitle);
                ((ImageView) itemView.findViewById(R.id.it3).findViewById(R.id.icon)).setImageResource(R.drawable.debit);
            }
            {
                remian = itemView.findViewById(R.id.it4).findViewById(R.id.txtDesc);
                TextView tv = itemView.findViewById(R.id.it4).findViewById(R.id.txtTv);
                tv.setText("مانده :");
                setTypeface(itemView.getContext(), remian, tv);
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
