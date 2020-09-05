package com.shizheng.lreanexpandablerectclervview.viewinit;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.shizheng.lreanexpandablerectclervview.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class NewChildViewGroup extends ChildViewHolder {
    private TextView tvChildTitle;
    private TextView tvChildSubtitle;
    private ImageView ivChildCoverImage;
    private CardView childCardView;

    public NewChildViewGroup(View itemView) {
        super(itemView);
        tvChildSubtitle = itemView.findViewById(R.id.child_text_subtitle);
        tvChildTitle = itemView.findViewById(R.id.child_text_title);
        ivChildCoverImage = itemView.findViewById(R.id.image_view_cover);
        childCardView = itemView.findViewById(R.id.child_card_view);
    }

    public void BindChildView(NameBean nameBean, Context context){
        tvChildTitle.setText(nameBean.getName());
        tvChildSubtitle.setText(nameBean.getCreator_name());
        Glide.with(context).load(nameBean.getImage_url()).into(ivChildCoverImage);
    }

    public ImageView getIvChildCoverImage(){
        return ivChildCoverImage;
    }

    public CardView getChildCardView() {
        return childCardView;
    }
}
