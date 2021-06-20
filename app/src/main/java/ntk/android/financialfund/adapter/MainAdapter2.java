package ntk.android.financialfund.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ntk.android.base.adapter.BaseRecyclerAdapter;
import ntk.android.financialfund.R;
import ntk.android.financialfund.model.PanelViewModel;

public class MainAdapter2 extends BaseRecyclerAdapter<PanelViewModel, MainAdapter2.vh> {


    PanelInterface myInterface;

    public MainAdapter2(List<PanelViewModel> PanelViewModels, PanelInterface panelInterface) {
        super(PanelViewModels);
        myInterface = panelInterface;
    }

    @Override
    public MainAdapter2.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new vh(inflate(parent, R.layout.item_main_list_2));
    }


    @Override
    public void onBindViewHolder(@NonNull MainAdapter2.vh holder, int position) { // called for every row in recycler view

        final PanelViewModel curr = getItem(position);
        if (curr.Title != null) {
            holder.setData(curr, position);

            holder.itemView.setOnClickListener(v -> myInterface.OnCardClick(curr));
        }

    }


    public class vh extends RecyclerView.ViewHolder {
        ImageView listimage;
        TextView listtitle;
        TextView engTitle;
        TextView badge;
        RelativeLayout hmlelist1;
        ImageView lockImae;

        public vh(View itemView) {
            super(itemView);

            listimage = (ImageView) itemView.findViewById(R.id.listimage);
            listtitle = (TextView) itemView.findViewById(R.id.listtitle);
            engTitle = (TextView) itemView.findViewById(R.id.engTitle);
            badge = (TextView) itemView.findViewById(R.id.badge);
            hmlelist1 = (RelativeLayout) itemView.findViewById(R.id.listcontainer1);
            lockImae = itemView.findViewById(R.id.luckImage);

//            View view_instance = (View) itemView.findViewById(R.id.parentbody);
//            ViewGroup.LayoutParams params = view_instance.getLayoutParams();
//            params.width= (int) GridCalculator.calculatewidth(context,(double)(GridCalculator.calculateNoOfColumnsimplicit(c)/2));
//            view_instance.setLayoutParams(params);
//            parentbody=(LinearLayout) itemView.findViewById(R.id.parentbody);

//            date = (TextView) itemView.findViewById(R.id.c1_item_date);
//            id = (TextView) itemView.findViewById(R.id.c1_item_id);
//            amount = (TextView) itemView.findViewById(R.id.c1_item_amount);
//            row_number = (TextView) itemView.findViewById(R.id.c1_row_number);
        }

        public void setData(PanelViewModel curr, int position) {
            listimage.setImageResource(curr.ImageId);
            listtitle.setText(curr.Title);
            if (curr.badgeCount > 0) {
                badge.setVisibility(View.VISIBLE);
                badge.setText("" + curr.badgeCount);
            } else
                badge.setVisibility(View.GONE);
        }

    }


}