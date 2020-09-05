package com.shizheng.lreanexpandablerectclervview.musiclistdetail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.shizheng.lreanexpandablerectclervview.R;

public class MusicListMainActivity extends AppCompatActivity {

    private String musicID = getIntent().getStringExtra("MUSIC_ID");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list_main_layout);
        initToolbar(getString(R.string.musiclistdetail));
        initImage(getIntent().getStringExtra("URL"));
        initText(getIntent().getStringExtra("TITLE"),
                getIntent().getStringExtra("AVATAR"),
                getIntent().getStringExtra("DESCRIPTION")
        );



    }



    public void initToolbar(String title){
        Toolbar toolbar = findViewById(R.id.music_list_tool_bar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    public void initImage(String url){
        ImageView imageView = findViewById(R.id.music_list_image_view);
        Glide.with(MusicListMainActivity.this).load(url).into(imageView);
    }

    public void initText(String s1,String s2,String s3){
        TextView tv1 = findViewById(R.id.music_list_text_view_title);
        TextView tv2 = findViewById(R.id.music_list_text_view_avatar);
        TextView tv3 = findViewById(R.id.music_list_text_view_detail);
        tv1.setText(s1);
        tv2.setText(s2);
        tv3.setText(s3);
    }

}
