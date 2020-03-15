package com.abhishekjagushte.careerblog;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class MyPostRecyclerViewAdapter extends RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Post> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyPostRecyclerViewAdapter(ArrayList<Post> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.postHeadline.setText(mValues.get(position).getHeadline());
        holder.postDate.setText(mValues.get(position).getDate());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("$$$$$$$$$$$$$$$$$", String.valueOf(mValues.size()));
        return mValues.size();
    }

    //ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView postHeadline;
        public final TextView postDate;
        public Post mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            postHeadline = (TextView) view.findViewById(R.id.post_headline);
            postDate = (TextView) view.findViewById(R.id.post_date);
            Log.d("SSSSSSSSSSSSSSSSS","RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        }

        @Override
        public String toString() {
            return super.toString() + " '" + postDate.getText() + "'";
        }
    }
}
