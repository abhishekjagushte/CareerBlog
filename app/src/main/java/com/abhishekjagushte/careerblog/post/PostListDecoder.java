package com.abhishekjagushte.careerblog.post;

import com.abhishekjagushte.careerblog.MainActivity;
import com.abhishekjagushte.careerblog.PostFragment;
import com.abhishekjagushte.careerblog.post.PostContent.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class PostListDecoder {

    private static String requestURL = "https://geeksforfuture.tk/wp-json/wp/v2/posts";

    private PostListDecoder(){
    }

    public static void makeHttpRequest() throws IOException, JSONException {


        final ArrayList<Post>[] posts = new ArrayList[]{new ArrayList<Post>()};
        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean done=true;

                do{
                    try{
                        URL url = null;
                        HttpURLConnection http = null;
                        InputStream inputStream = null;

                        url = new URL(requestURL);
                        http = (HttpURLConnection) url.openConnection();
                        http.setReadTimeout(10000 /* milliseconds */);
                        http.setConnectTimeout(15000 /* milliseconds */);
                        http.setRequestMethod("GET");
                        http.connect();

                        if(http.getResponseCode() == 200){
                            inputStream = http.getInputStream();
                            posts[0] = parseJSON(readInputStream(inputStream));

                            MainActivity.handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    PostFragment.adapter.notifyDataSetChanged();
                                    PostFragment.sliderAdapter.notifyDataSetChanged();
                                }
                            });

                            done = false;
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }while(done);


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

        JSONArray response = new JSONArray(JSON);

        ArrayList<Post> post_list = new ArrayList<>();
        for(int i =0;i<=9;i++)
        {
            JSONObject postJSON = response.getJSONObject(i);

            JSONObject contentJSON = postJSON.getJSONObject("content");
            String content = contentJSON.getString("rendered");

            Document doc = Jsoup.parse(content);
            Elements e = doc.select("img[src]");

            String img = e.attr("src");
            System.out.println(img);

            int id = postJSON.getInt("id");

            JSONObject titleJSON = postJSON.getJSONObject("title");

            String title = titleJSON.getString("rendered");
            String date = postJSON.getString("date");

            Post post = new Post(title, "Admin", date, id, img);
            post_list.add(post);
            PostContent.ITEMS.add(post);

        }
        return post_list;
    }
}
