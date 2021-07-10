package ntk.android.financialfund.adapter.drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ntk.android.base.adapter.BaseRecyclerAdapter;
import ntk.android.base.room.NotificationStorageService;
import ntk.android.base.utill.FontManager;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.PanelInterface;
import ntk.android.financialfund.model.PanelViewModel;

public class DrawerAdapter extends BaseRecyclerAdapter<PanelViewModel, DrawerAdapter.ViewHolder> {
    PanelInterface myInterface;

    private FlowingDrawer drawer;

    public DrawerAdapter(List< PanelViewModel> children, FlowingDrawer drawer, PanelInterface panelInterface) {
        super(children);
        this.drawer = drawer;
        myInterface = panelInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_drawer_child, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.Img.setImageResource(getItem(position).ImageId);
        if (getItem(position).tag == 7) {
            int n = new NotificationStorageService().getAllUnread(holder.itemView.getContext()).size();
            if (n != 0) {
                holder.Lbls.get(1).setText(String.valueOf(n));
                holder.Lbls.get(1).setVisibility(View.VISIBLE);
            }
        }
        holder.Lbls.get(0).setText(getItem(position).Title);

        holder.Root.setOnClickListener(v -> {
            drawer.closeMenu(true);
            myInterface.OnCardClick(getItem(position));});
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ImgRecyclerDrawerChild)
        ImageView Img;

        @BindViews({R.id.lblRecyclerDrawerChild, R.id.lblBadgeRecyclerDrawerChild})
        List<TextView> Lbls;

        @BindView(R.id.RootContainerRecyclerDrawerChild)
        RelativeLayout Root;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            Lbls.get(0).setTypeface(FontManager.T1_Typeface(view.getContext()));
            Lbls.get(1).setTypeface(FontManager.T1_Typeface(view.getContext()));
        }
    }

}
