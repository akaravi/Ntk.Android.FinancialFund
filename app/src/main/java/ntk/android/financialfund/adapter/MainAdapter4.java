package ntk.android.financialfund.adapter;

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
import ntk.android.financialfund.model.PanelViewModel;

public class MainAdapter4 extends BaseRecyclerAdapter<PanelViewModel, MainAdapter4.vh> {


    PanelInterface myInterface;

    public MainAdapter4(List<PanelViewModel> PanelViewModels, PanelInterface panelInterface) {
        super(PanelViewModels);
        myInterface = panelInterface;
    }

    @Override
    public MainAdapter4.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new vh(inflate(parent, R.layout.item_main_list_4));
    }


    @Override
    public void onBindViewHolder(@NonNull MainAdapter4.vh holder, int position) { // called for every row in recycler view

        final PanelViewModel curr = getItem(position);
        if (curr.Title != null) {
            holder.setData(curr, position);

            holder.itemView.setOnClickListener(v -> myInterface.OnCardClick(curr));
        }

    }


    public class vh extends RecyclerView.ViewHolder {
        ImageView listimage;
        TextView listtitle;

        public vh(View itemView) {
            super(itemView);

            listimage = (ImageView) itemView.findViewById(R.id.listimage);
            listtitle = (TextView) itemView.findViewById(R.id.listtitle);
            listtitle.setTypeface(FontManager.T1_Typeface(itemView.getContext()));

        }

        public void setData(PanelViewModel curr, int position) {
            listimage.setImageResource(curr.ImageId);
            listtitle.setText(curr.Title);
        }

    }


}