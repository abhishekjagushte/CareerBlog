package com.abhishekjagushte.careerblog.post;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;
import org.sufficientlysecure.htmltextview.HtmlTextView;

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

    public static void makeHttpRequest(final Post post, final TextView view, final WebView webView, final HtmlTextView tv) throws IOException, JSONException {

        final ArrayList<Post>[] posts = new ArrayList[]{new ArrayList<Post>()};
        final String TAG = "PostContentDecoder";

        final int id = post.getId();
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {

                try{

                    URL url = null;
                    HttpURLConnection http = null;
                    InputStream inputStream = null;

                    url = new URL(requestURL+Integer.toString(id));
                    http = (HttpURLConnection) url.openConnection();
                    http.setReadTimeout(10000 /* milliseconds */);
                    http.setConnectTimeout(15000 /* milliseconds */);
                    http.setRequestMethod("GET");
                    http.connect();

                    if(http.getResponseCode() == 200){
                        inputStream = http.getInputStream();
                        final String content = parseJSON(readInputStream(inputStream));
                        post.setContent(content);

                        MainActivity.handler.post(new Runnable() {
                            @Override
                            public void run() {

                                tv.setHtml(content);
                                //webView.loadData(content, "text/html", "UTF-8");
                                //view.setText(contentspanned);
                                //Linkify.addLinks(view,Linkify.ALL);
                                //view.setMovementMethod(LinkMovementMethod.getInstance());
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

    private static String parseJSON(String JSON) throws JSONException {
        JSONObject response = new JSONObject(JSON);
        JSONObject contentJSON = response.getJSONObject("content");
        String content = contentJSON.getString("rendered");

        return content;
    }
}
