package com.abhishekjagushte.careerblog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeaturedPostViewHolder extends RecyclerView.ViewHolder {

    public FeaturedPostViewHolder(@NonNull View itemView) {
        super(itemView);
        ImageView imageView = itemView.findViewById(R.id.featured_post_image);
        TextView title = itemView.findViewById(R.id.featured_post_title);
    }
}
