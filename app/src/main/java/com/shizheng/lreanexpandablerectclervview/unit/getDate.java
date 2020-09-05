package com.shizheng.lreanexpandablerectclervview.unit;

import com.shizheng.lreanexpandablerectclervview.BuildConfig;
import com.shizheng.lreanexpandablerectclervview.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class getDate {

    public static JSONArray getUrlArrayList(String url) throws IOException, JSONException {

        Connection connection = Jsoup.connect(url);
        connection.ignoreContentType(true);
        connection.timeout(2000);
        Document document = connection.get();
        String json_object_string = document.body().text();
        JSONObject json_object = new JSONObject(json_object_string);

        return json_object.getJSONArray("playlist");
    }

    public static String getUserName(String completely_url) throws JSONException, IOException {

        Connection connection_detail = Jsoup.connect(completely_url);
        connection_detail.ignoreContentType(true);
        connection_detail.timeout(2000);
        Document document_detail = connection_detail.get();
        JSONObject object = new JSONObject(document_detail.body().text());
        JSONObject json_profile = object.getJSONObject("profile");
        return json_profile.getString("nickname");
    }

    public static String getAvatarImageUrl(String completely_url) throws JSONException, IOException {

        Connection connection_detail = Jsoup.connect(completely_url);
        connection_detail.ignoreContentType(true);
        connection_detail.timeout(2000);
        Document document_detail = connection_detail.get();
        JSONObject object = new JSONObject(document_detail.body().text());
        JSONObject json_profile = object.getJSONObject("profile");
        return json_profile.getString("avatarUrl");
    }

    public static String getFolloweds(String completely_url) throws JSONException, IOException {
        Connection connection_detail = Jsoup.connect(completely_url);
        connection_detail.ignoreContentType(true);
        connection_detail.timeout(2000);
        Document document_detail = connection_detail.get();
        JSONObject object = new JSONObject(document_detail.body().text());
        JSONObject json_profile = object.getJSONObject("profile");
        int followNum = json_profile.getInt("followeds");
        return Integer.toString(followNum);
    }

    public static String getFollows(String completely_url) throws IOException, JSONException {

        Connection connection_detail = Jsoup.connect(completely_url);
        connection_detail.ignoreContentType(true);
        connection_detail.timeout(2000);
        Document document_detail = connection_detail.get();
        JSONObject object = new JSONObject(document_detail.body().text());
        JSONObject json_profile = object.getJSONObject("profile");
        int followNum = json_profile.getInt("follows");
        return Integer.toString(followNum);
    }

    public static int getPlaylistReturnCode(String url) throws JSONException, IOException {
        Connection connection = Jsoup.connect(url);
        connection.ignoreContentType(true);
        connection.timeout(2000);
        Document document = connection.get();
        String json_object_string = document.body().text();
        JSONObject json_object = new JSONObject(json_object_string);
        return json_object.getInt("code");
    }

    public static String getMusicDate(String musicId){
        String complete_url = Api.musicDetailUrl + musicId;
        return null;
    }

    public static String getMusicListDate(String musicListId) throws IOException, JSONException {
        String complete_url = Api.musicListDetailUrl + musicListId;
        Connection connection = Jsoup.connect(complete_url);
        connection.ignoreContentType(true);
        connection.timeout(2000);
        Document document = connection.get();
        JSONObject jsonObject = new JSONObject(document.body().text());
        JSONObject playList_jsonObject = jsonObject.getJSONObject("playlist");
        JSONArray jsonArray = playList_jsonObject.getJSONArray("trackIds");
        for (int io = 0;io<jsonArray.length();io++) {
            long l = jsonArray.getJSONObject(io).getLong("id");
            return Long.toString(l);
        }
        return null;
    }


}
