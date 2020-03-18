package com.abhishekjagushte.careerblog.post;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.abhishekjagushte.careerblog.MainActivity;
import com.abhishekjagushte.careerblog.MyPostRecyclerViewAdapter;
import com.abhishekjagushte.careerblog.PostFragment;
import com.abhishekjagushte.careerblog.post.PostContent.Post;

public class PostListDecoder {

    private static String requestURL = "https://geeksforfuture.tk/wp-json/wp/v2/posts";

    private PostListDecoder(){
    }

    public static void makeHttpRequest() throws IOException, JSONException {


        final ArrayList<Post>[] posts = new ArrayList[]{new ArrayList<Post>()};
        final URL[] url = {null};
        final HttpURLConnection[] http = {null};
        final InputStream[] inputStream = {null};

        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    url[0] = new URL(requestURL);
                    http[0] = (HttpURLConnection) url[0].openConnection();
                    http[0].setReadTimeout(10000 /* milliseconds */);
                    http[0].setConnectTimeout(15000 /* milliseconds */);
                    http[0].setRequestMethod("GET");
                    http[0].connect();

                    if(http[0].getResponseCode() == 200){
                        inputStream[0] = http[0].getInputStream();
                        posts[0] = parseJSON(readInputStream(inputStream[0]));

                        MainActivity.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                PostFragment.adapter.notifyDataSetChanged();
                                PostFragment.sliderAdapter.notifyDataSetChanged();
                            }
                        });

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        if(inputStream!=null)
        {
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bf = new BufferedReader(reader);
            String line = bf.readLine();
            while(line!=null)
            {
                builder.append(line);
                line = bf.readLine();
            }
        }
        return builder.toString();
    }

    private static ArrayList<Post> parseJSON(String JSON) throws JSONException {
        //JSONObject response = new JSONObject(JSON);

        JSONArray response = new JSONArray(JSON);

        ArrayList<Post> post_list = new ArrayList<>();
        for(int i =0;i<=9;i++)
        {
            JSONObject postJSON = response.getJSONObject(i);

            int id = postJSON.getInt("id");

            JSONObject titleJSON = postJSON.getJSONObject("title");

            String title = titleJSON.getString("rendered");
            String date = postJSON.getString("date");

            Log.d("Decode","*********************"+i);

            Post post = new Post(title, "Admin", date, id);
            post_list.add(post);
            PostContent.ITEMS.add(post);

        }

        return post_list;
    }
}
