package com.shizheng.lreanexpandablerectclervview.musiclistdetail;

public class MusicListBean {

    private String musicTitle;
    private String musicActor;
    private String coverImageUrl;

    public MusicListBean(String musicTitle,String musicActor,String coverImageUrl){
        this.coverImageUrl = coverImageUrl;
        this.musicActor = musicActor;
        this.musicTitle = musicTitle;
    }

    public String getMusicActor() {
        return musicActor;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }
}
