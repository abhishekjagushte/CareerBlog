package com.abhishekjagushte.careerblog.post;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

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
import com.abhishekjagushte.careerblog.PostPage;
import com.abhishekjagushte.careerblog.R;
import com.abhishekjagushte.careerblog.post.PostContent.Post;

public class PostContentDecoder {

    private static String requestURL = "https://geeksforfuture.tk/wp-json/wp/v2/posts/";

    private PostContentDecoder(){
    }

    public static void makeHttpRequest(Post post, final TextView view) throws IOException, JSONException {

        final ArrayList<Post>[] posts = new ArrayList[]{new ArrayList<Post>()};
        final URL[] url = {null};
        final HttpURLConnection[] http = {null};
        final InputStream[] inputStream = {null};

        final int id = post.getId();
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {

                try{
                    url[0] = new URL(requestURL+Integer.toString(id));
                    http[0] = (HttpURLConnection) url[0].openConnection();
                    http[0].setReadTimeout(10000 /* milliseconds */);
                    http[0].setConnectTimeout(15000 /* milliseconds */);
                    http[0].setRequestMethod("GET");
                    http[0].connect();

                    if(http[0].getResponseCode() == 200){
                        inputStream[0] = http[0].getInputStream();
                        final String content = parseJSON(readInputStream(inputStream[0]));
                        final Spanned contentspanned = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);

                        MainActivity.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.setText(contentspanned);
                                Linkify.addLinks(view,Linkify.ALL);
                                view.setMovementMethod(LinkMovementMethod.getInstance());
                            }
                        });
                        return;

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

    private static String parseJSON(String JSON) throws JSONException {
        JSONObject response = new JSONObject(JSON);
        JSONObject contentJSON = response.getJSONObject("content");
        String content = contentJSON.getString("rendered");

        return content;
    }
}
