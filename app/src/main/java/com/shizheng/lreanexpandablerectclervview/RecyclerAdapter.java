package com.shizheng.lreanexpandablerectclervview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.shizheng.lreanexpandablerectclervview.viewinit.NameBean;
import com.shizheng.lreanexpandablerectclervview.viewinit.NewChildViewGroup;
import com.shizheng.lreanexpandablerectclervview.viewinit.NewGroupViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class RecyclerAdapter extends ExpandableRecyclerViewAdapter<NewGroupViewHolder, NewChildViewGroup> {

    private Context context;
    private RecyclerAdapter.onChildClickListener onChildClickListener;
    private ImageView coverImage;
    private CardView childCardView;

    public RecyclerAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        this.context = context;
    }


    @Override
    public NewGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.group_view, parent,
                false);
        return new NewGroupViewHolder(view);
    }

    @Override
    public NewChildViewGroup onCreateChildViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.child_view, parent,
                false);
        return new NewChildViewGroup(view);
    }

    @Override
    public void onBindChildViewHolder(final NewChildViewGroup holder, final int flatPosition,
                                      final ExpandableGroup group, final int childIndex) {

        final NameBean nameBean = (NameBean) group.getItems().get(childIndex);
        holder.BindChildView(nameBean, context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChildClickListener.OnChildClick(view,nameBean,childIndex,holder.getIvChildCoverImage());
            }
        });
        this.childCardView = holder.getChildCardView();
        this.coverImage = holder.getIvChildCoverImage();
    }

    @Override
    public void onBindGroupViewHolder(NewGroupViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {

        holder.setGroupText(group);

    }

    public void expandGroup(int i) {
        if (!this.isGroupExpanded(i)) {
            toggleGroup(i);
        }
    }

    public void setOnChlidClickListener(RecyclerAdapter.onChildClickListener childClickListener) {
        this.onChildClickListener = childClickListener;
    }

    public interface onChildClickListener {
         void OnChildClick(View view, NameBean nameBean,int position,ImageView iv1);
    }

    public CardView getChildCardView() {
        return childCardView;
    }

    public ImageView getCoverImage() {
        return coverImage;
    }
}
