package ntk.android.financialfund.adapter;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ntk.android.financialfund.R;
import ntk.android.financialfund.model.PanelViewModel;

public class PanelSlidingAdapter extends PagerAdapter {

    private List<PanelViewModel> thirdList;
    private List<PanelViewModel> frislList = new ArrayList<>();
    private List<PanelViewModel> secondList = new ArrayList<>();
    private PanelAdapter adapter;
    PanelInterface myInterface;
    int PageCount;

    public PanelSlidingAdapter(PanelInterface panelInterface, List<PanelViewModel>... models) {
        frislList = models[0];
        secondList = models[1];
        PageCount = models.length;
        myInterface = panelInterface;
    }

    public void Updatelist(List<PanelViewModel> PanelViewModel, List<PanelViewModel> instalmen2, List<PanelViewModel> tab3) {
        frislList = PanelViewModel;
        secondList = instalmen2;
        thirdList = tab3;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getCount() {

        return PageCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = LayoutInflater.from(view.getContext()).inflate(R.layout.layout_paneladapter, view, false);
        if (position == 0) {
            RecyclerView rv = imageLayout.findViewById(R.id.rv_parent);

            GridLayoutManager llm = new GridLayoutManager(view.getContext(), 3, GridLayoutManager.VERTICAL, false);
            try {
                adapter = new PanelAdapter(frislList, myInterface);
                rv.setAdapter(adapter);
                rv.setLayoutManager(llm);
//                rv.addItemDecoration(new SimpleDividerItemDecoration(view.getContext().getResources().getDimensionPixelSize(R.dimen.photos_list_spacing),
//                        3));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (position == 1) {
            RecyclerView rv = (RecyclerView) imageLayout.findViewById(R.id.rv_parent);
            GridLayoutManager llm = new GridLayoutManager(view.getContext(), 3, GridLayoutManager.VERTICAL, false);
            try {
                adapter = new PanelAdapter(secondList, myInterface);
                rv.setAdapter(adapter);
                rv.setLayoutManager(llm);
//                rv.addItemDecoration(new SimpleDividerItemDecoration(view.getContext().getResources().getDimensionPixelSize(R.dimen.photos_list_spacing),
//                        3));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            RecyclerView rv = (RecyclerView) imageLayout.findViewById(R.id.rv_parent);
            GridLayoutManager llm = new GridLayoutManager(view.getContext(), 3, GridLayoutManager.VERTICAL, false);
            try {
                adapter = new PanelAdapter(thirdList, myInterface);
                rv.setAdapter(adapter);
                rv.setLayoutManager(llm);
//                rv.addItemDecoration(new SimpleDividerItemDecoration(view.getContext().getResources().getDimensionPixelSize(R.dimen.photos_list_spacing),
//                        3));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
