package ntk.android.financialfund.activity;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ntk.android.base.activity.blog.BaseBlogDetail_1_Activity;
import ntk.android.base.entitymodel.blog.BlogCommentModel;
import ntk.android.base.entitymodel.blog.BlogContentOtherInfoModel;
import ntk.android.financialfund.R;
import ntk.android.financialfund.adapter.BlogCommentAdapter;
import ntk.android.financialfund.adapter.TabBlogAdapter;

public class BlogDetailActivity extends BaseBlogDetail_1_Activity {
    @Override
    protected void initChild() {
        favoriteDrawableId = R.drawable.ic_fav_full;
        unFavoriteDrawableId = R.drawable.ic_fav;
    }

    @Override
    public RecyclerView.Adapter createCommentAdapter(List<BlogCommentModel> listItems) {
        return new BlogCommentAdapter(this, listItems);
    }

    @Override
    protected RecyclerView.Adapter createOtherInfoAdapter(List<BlogContentOtherInfoModel> info) {
        return new TabBlogAdapter(this, info);
    }
}

