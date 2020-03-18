package com.abhishekjagushte.careerblog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.abhishekjagushte.careerblog.post.PostContent;
import com.abhishekjagushte.careerblog.post.PostContent.Post;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter {

    private PostFragment.OnSliderClickedListener listener;

    public SliderAdapter(PostFragment.OnSliderClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_post, null);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if(position<5){
            final Post post = PostContent.ITEMS.get(position);
            ((SliderViewHolder)viewHolder).title.setText(post.getHeadline());
            ((SliderViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSliderClicked(post);
                }
            });

        }
    }

    @Override
    public int getCount() {
        if(PostContent.ITEMS.size()>5)
            return 5;
        else
            return PostContent.ITEMS.size();
    }

    public class SliderViewHolder extends SliderAdapter.ViewHolder{
        public ImageView featured_image;
        public TextView title;
        public View itemView;

        public SliderViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            featured_image = itemView.findViewById(R.id.featured_post_image);
            title = itemView.findViewById(R.id.featured_post_title);
        }
    }


}
