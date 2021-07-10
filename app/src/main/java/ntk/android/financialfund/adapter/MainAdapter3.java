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
import ntk.android.base.utill.FontManager;
import ntk.android.financialfund.R;
import ntk.android.financialfund.model.PanelViewModel;

public class MainAdapter3 extends BaseRecyclerAdapter<PanelViewModel, MainAdapter3.vh> {

    PanelInterface myInterface;
    int Height;
    public MainAdapter3(int height,List<PanelViewModel> PanelViewModels, PanelInterface panelInterface) {
        super(PanelViewModels);
        myInterface = panelInterface;
        Height=height;
    }

    @Override
    public MainAdapter3.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflate(parent, R.layout.item_main_list_3);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height=Height;
        view.setLayoutParams(layoutParams);
        return new vh(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MainAdapter3.vh holder, int position) { // called for every row in recycler view

        final PanelViewModel curr = getItem(position);
        if (curr.Title != null) {
            holder.setData(curr, position);

            holder.itemView.setOnClickListener(v -> myInterface.OnCardClick(curr));
        }

    }


    public class vh extends RecyclerView.ViewHolder {
        ImageView listimage;
        TextView listtitle;
//        TextView badge;
        ImageView lockImae;

        public vh(View itemView) {
            super(itemView);

            listimage = (ImageView) itemView.findViewById(R.id.listimage);
            listtitle = (TextView) itemView.findViewById(R.id.listtitle);
//            badge = (TextView) itemView.findViewById(R.id.badge);
            lockImae = itemView.findViewById(R.id.luckImage);
            listtitle.setTypeface(FontManager.T1_Typeface(itemView.getContext()));
        }

        public void setData(PanelViewModel curr, int position) {
            listimage.setImageResource(curr.ImageId);
            listtitle.setText(curr.Title);
//            if (curr.badgeCount > 0) {
//                badge.setVisibility(View.VISIBLE);
//                badge.setText("" + curr.badgeCount);
//            } else
//                badge.setVisibility(View.GONE);
        }

    }


}