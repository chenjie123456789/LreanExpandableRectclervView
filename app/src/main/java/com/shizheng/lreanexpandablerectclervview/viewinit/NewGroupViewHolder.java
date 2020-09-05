package com.shizheng.lreanexpandablerectclervview.viewinit;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shizheng.lreanexpandablerectclervview.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class NewGroupViewHolder extends GroupViewHolder {

    private TextView tvTitle;
    private ImageView ivArrow;

    public NewGroupViewHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.expandable_title_text);
        ivArrow = itemView.findViewById(R.id.ic_group_arrow);
    }

    public void setGroupText(@org.jetbrains.annotations.NotNull ExpandableGroup titleBean){
        tvTitle.setText(titleBean.getTitle());
    }

    @Override
    public void expand() {
        super.expand();
        ivArrow.animate().setDuration(500).rotation(180).start();
    }

    @Override
    public void collapse() {
        super.collapse();
        ivArrow.animate().setDuration(500).rotation(0).start();
    }
}
