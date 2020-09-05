package com.shizheng.lreanexpandablerectclervview;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.shizheng.lreanexpandablerectclervview.api.Api;
import com.shizheng.lreanexpandablerectclervview.musiclistdetail.MusicListMainActivity;
import com.shizheng.lreanexpandablerectclervview.unit.InputDialog;
import com.shizheng.lreanexpandablerectclervview.unit.getDate;
import com.shizheng.lreanexpandablerectclervview.viewinit.BaseTitleBean;
import com.shizheng.lreanexpandablerectclervview.viewinit.NameBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private InputDialog dialog;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 3) {
                dialog.setDialogProgressBarGone();
                dialog.setEditTextError((String) msg.obj);
                return;
            } else if (msg.what == 1) {
                JSONArray dateArray = (JSONArray) msg.obj;
                try {
                    initRecyclerView(dateArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }

            if (msg.what == 2) {
                String[] userDate = (String[]) msg.obj;
                initUserDetail(userDate[0], userDate[1], userDate[3], userDate[2]);
                initToolbar(userDate[0]);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog();
    }

    //实例化Toolbar
    public void initToolbar(String text) {
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.main_collapsing_toolbar_layout);
        Toolbar toolbar = findViewById(R.id.main_tool_bar);
        collapsingToolbarLayout.setTitle(text);
        setSupportActionBar(toolbar);
    }

    //获取用户相关信息
    /*
    Strings -> 4
     */
    public void initUserDetail(String name, String url, String attract, String fans) {
        TextView tvNickName = findViewById(R.id.user_name_text);
        TextView tvAttractNum = findViewById(R.id.attract_number);
        TextView tvFansNum = findViewById(R.id.fans_number);
        ImageView ivImageUrl = findViewById(R.id.user_image);
        tvNickName.setText(name);
        tvAttractNum.setText(attract);
        tvFansNum.setText(fans);
        Glide.with(MainActivity.this).load(url).into(ivImageUrl);

    }

    //对话框
    /*
    当然，是自定义view。
    自带加载进度条
     */
    public void initDialog() {
        dialog = new InputDialog(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle(R.string.dialog_title_text);
        dialog.setOnDialogButtonClick(new InputDialog.onDialogButtonClick() {
            @Override
            public void onHitUp(View view) {
                dialog.setDialogProgressBarVisibility();
                Thread thread = new Thread(new onBackgroundDoIt());
                thread.start();
            }
        });
        dialog.show();
    }

    //实例化RecyclerView

    /**
     * JSONArray date
     */
    public void initRecyclerView(JSONArray dateBase) throws JSONException {

        //实例化RecyclerView的一系列操作
        String uid = dialog.getEditTextString();
        RecyclerView mainRecyclerView = findViewById(R.id.main_recycler_view);
        final List<NameBean> nameBeanList01 = new ArrayList<>();
        List<NameBean> nameBeanList02 = new ArrayList<>();

        for (int i = 0; i < dateBase.length(); i++) {
            JSONObject object = dateBase.getJSONObject(i);
            JSONObject creatorJsonDate = object.getJSONObject("creator");
            int id = object.getInt("userId");
            //开始循环添加。
            //获取信息。
            //信息：json获取。
            if (id == Integer.parseInt(uid)) {
                //私人歌单
                String coverName = object.getString("name");
                String creatorName = creatorJsonDate.getString("nickname");
                String coverImageUrl = object.getString("coverImgUrl");
                long music_id = object.getLong("id");
                String music_id_text = Long.toString(music_id);
                String introduction = object.getString("description");
                nameBeanList01.add(new NameBean(coverName, creatorName, coverImageUrl, music_id_text, introduction));
            } else {
                //收藏歌单
                String coverName = object.getString("name");
                String creatorName = creatorJsonDate.getString("nickname");
                String coverImageUrl = object.getString("coverImgUrl");
                long music_id = object.getLong("id");
                String music_id_text = Long.toString(music_id);
                String introduction = object.getString("description");
                nameBeanList02.add(new NameBean(coverName, creatorName, coverImageUrl, music_id_text, introduction));
            }
        }

        final List<BaseTitleBean> baseTitleBeans = new ArrayList<>();
        baseTitleBeans.add(new BaseTitleBean(getString(R.string.my_build), nameBeanList01));
        baseTitleBeans.add(new BaseTitleBean(getString(R.string.my_collected), nameBeanList02));

        final RecyclerAdapter adapter = new RecyclerAdapter(baseTitleBeans, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mainRecyclerView.setLayoutManager(layoutManager);
        adapter.expandGroup(0);
        mainRecyclerView.setAdapter(adapter);
        adapter.setOnChlidClickListener(new RecyclerAdapter.onChildClickListener() {
            @Override
            public void OnChildClick(View view, NameBean nameBean, int position, ImageView iv1) {
                Intent intent = new Intent(MainActivity.this, MusicListMainActivity.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        MainActivity.this,
                        new Pair<View, String>(iv1,
                                getString(R.string.imageName)));
                intent.putExtra("URL", nameBean.getImage_url());
                intent.putExtra("TITLE", nameBean.getName());
                intent.putExtra("AVATAR", nameBean.getCreator_name());
                intent.putExtra("MUSIC_ID", nameBean.getMusic_list_id());
                intent.putExtra("DESCRIPTION",nameBean.getIntroduction());
                startActivity(intent, activityOptions.toBundle());
            }

        });
    }

    class onBackgroundDoIt implements Runnable {

        @Override
        public void run() {
            try {
                //获取播放列表
                String user_id = dialog.getEditTextString();
                String mainUrl = Api.baseUrl + user_id;
                String mainUrlForGetUserDetail = Api.userUrl + user_id;

                Message message = new Message();
                //判断是否为正常id
                //原理：判断id所返回的是否为[]
                //同时，检测下json数据的返回值（code）是否为200。
                JSONArray dateBase = getDate.getUrlArrayList(mainUrl);
                if (dateBase.toString().equals("[]") || getDate.getPlaylistReturnCode(mainUrl) != 200) {
                    message.what = 3;
                    message.obj = getString(R.string.error);
                } else {
                    message.obj = dateBase;
                    message.what = 1;
                }
                handler.sendMessage(message);
                //获取用户名称，用户头像链接
                //注意：glide可以在主线程中加载
                Message messageUserDetail = new Message();
                String[] userDateBase = {getDate.getUserName(mainUrlForGetUserDetail),
                        getDate.getAvatarImageUrl(mainUrlForGetUserDetail),
                        getDate.getFolloweds(mainUrlForGetUserDetail),
                        getDate.getFollows(mainUrlForGetUserDetail)};
                messageUserDetail.what = 2;
                messageUserDetail.obj = userDateBase;
                handler.sendMessage(messageUserDetail);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        }
    }

}