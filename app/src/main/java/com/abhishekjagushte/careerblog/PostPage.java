package com.abhishekjagushte.careerblog;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishekjagushte.careerblog.post.DownloadImageTask;
import com.abhishekjagushte.careerblog.post.PostContent.Post;
import com.abhishekjagushte.careerblog.post.PostContentDecoder;

import org.json.JSONException;

import java.io.IOException;

public class PostPage extends Fragment {

    private Post post;

    public PostPage(Post post) {
        this.post = post;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_post_page, container, false);

        TextView post_title = fragment.findViewById(R.id.post_title);
        TextView post_content = fragment.findViewById(R.id.post_content);
        ImageView imageView = fragment.findViewById(R.id.post_imageview);

        post_title.setText(post.getHeadline());

        new DownloadImageTask(imageView).execute(post.getImageURL());

        //post_content.setTextColor(getColorFromAttr(R.attr.text_color));

        try {
            PostContentDecoder.makeHttpRequest(post,post_content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    private int getColorFromAttr(int attr)
    {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getActivity().getTheme();
        theme.resolveAttribute(attr,typedValue,true);
        @ColorRes int color = typedValue.resourceId;
        return color;
    }


}
