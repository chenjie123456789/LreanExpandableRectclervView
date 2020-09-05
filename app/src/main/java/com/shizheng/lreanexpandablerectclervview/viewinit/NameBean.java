package com.shizheng.lreanexpandablerectclervview.viewinit;

import android.os.Parcel;
import android.os.Parcelable;

public class NameBean implements Parcelable {

    public static final Creator<NameBean> CREATOR = new Creator<NameBean>() {
        @Override
        public NameBean createFromParcel(Parcel in) {
            return new NameBean(in);
        }

        @Override
        public NameBean[] newArray(int size) {
            return new NameBean[size];
        }
    };
    private String name;
    private String image_url;
    private String creator_name;
    private String music_list_id;
    private String introduction;

    public NameBean(String name, String creatorName, String image_url,String musicID,String introduction) {
        this.name = name;
        this.creator_name = creatorName;
        this.image_url = image_url;
        this.music_list_id = musicID;
        this.introduction = introduction;
    }

    public NameBean(Parcel in) {
        name = in.readString();
        image_url = in.readString();
        creator_name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image_url);
        dest.writeString(creator_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public String getMusic_list_id() {
        return music_list_id;
    }

    public String getIntroduction() {
        return introduction;
    }
}
