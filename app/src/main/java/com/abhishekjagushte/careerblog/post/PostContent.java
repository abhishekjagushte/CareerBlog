package com.abhishekjagushte.careerblog.post;


import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostContent {

    /**
     * An array of sample (dummy) items.
     */
    public static ArrayList<Post> ITEMS = new ArrayList<>();


//    static {
//        try {
//            ITEMS = PostListDecoder.makeHttpRequest();
//            Log.d("SSSSSSSSSSSSS",ITEMS.get(0).getHeadline());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class Post {

        public Post(String headline, String writer, String date, int id) {
            this.id = id;
            this.headline = headline;
            this.writer = writer;
            this.date = date;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getWriter() {
            return writer;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        private String headline;
        private String writer;
        private String date;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private  int id;
    }
}
