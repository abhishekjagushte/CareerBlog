package com.abhishekjagushte.careerblog.post;
import java.util.ArrayList;

public class PostContent {

    /**
     * An array of sample (dummy) items.
     */
    public static ArrayList<Post> ITEMS = new ArrayList<>();

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class Post {

        public String imageURL;

        public Post(String headline, String writer, String date, int id, String imageURL) {
            this.id = id;
            this.headline = headline;
            this.writer = writer;
            this.date = date;
            this.imageURL = imageURL;
        }


        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
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
