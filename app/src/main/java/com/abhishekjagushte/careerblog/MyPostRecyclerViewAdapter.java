package com.abhishekjagushte.careerblog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishekjagushte.careerblog.PostFragment.OnListFragmentInteractionListener;
import com.abhishekjagushte.careerblog.dummy.DummyContent.DummyItem;
import com.abhishekjagushte.careerblog.post.PostContent;
import com.abhishekjagushte.careerblog.post.PostContent.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPostRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FEATURED = 1;
    private static final int TYPE_NORMAL = 2;
    private final ArrayList<Post> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyPostRecyclerViewAdapter(ArrayList<Post> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_FEATURED)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.featured_post, parent, false);
            return new FeaturedPostViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_post, parent, false);
            return new NormalPostViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==TYPE_FEATURED){
            ((FeaturedPostViewHolder)holder).mItem = mValues.get(position);
            ((FeaturedPostViewHolder)holder).title.setText(mValues.get(position).getHeadline());
            //((FeaturedPostViewHolder)holder).imageView.setSrc(mValues.get(position).getDate());

            ((FeaturedPostViewHolder)holder).mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(((FeaturedPostViewHolder)holder).mItem);
                    }
                }
            });
        }
        else{
            ((NormalPostViewHolder)holder).mItem = mValues.get(position);
            ((NormalPostViewHolder)holder).postHeadline.setText(mValues.get(position).getHeadline());
            ((NormalPostViewHolder)holder).postDate.setText(mValues.get(position).getDate());

            ((NormalPostViewHolder)holder).mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(((NormalPostViewHolder)holder).mItem);
                    }
                }
            });
        }
    }

//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.postHeadline.setText(mValues.get(position).getHeadline());
//        holder.postDate.setText(mValues.get(position).getDate());
//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        Log.d("$$$$$$$$$$$$$$$$$", String.valueOf(mValues.size()));
        return mValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position<0) {
            return TYPE_FEATURED;
        } else {
            return TYPE_NORMAL;
        }
    }

    //ViewHolder class
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;
//        public final TextView postHeadline;
//        public final TextView postDate;
//        public Post mItem;
//
//        public ViewHolder(View view) {
//            super(view);
//            mView = view;
//            postHeadline = (TextView) view.findViewById(R.id.post_headline);
//            postDate = (TextView) view.findViewById(R.id.post_date);
//            Log.d("SSSSSSSSSSSSSSSSS","RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + " '" + postDate.getText() + "'";
//        }
//    }


    //ViewHolder class
    public class NormalPostViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView postHeadline;
        public final TextView postDate;
        public Post mItem;

        public NormalPostViewHolder(View view) {
            super(view);
            mView = view;
            postHeadline = (TextView) view.findViewById(R.id.post_headline);
            postDate = (TextView) view.findViewById(R.id.post_date);
        }

    }

    public class FeaturedPostViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView title;
        public final View mView;
        public Post mItem;

        public FeaturedPostViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            imageView = itemView.findViewById(R.id.featured_post_image);
            title = itemView.findViewById(R.id.featured_post_title);
        }
    }

}
