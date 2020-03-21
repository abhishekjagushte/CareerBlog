package com.abhishekjagushte.careerblog.post;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.abhishekjagushte.careerblog.MainActivity;
import com.abhishekjagushte.careerblog.PostFragment;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    @SuppressLint("StaticFieldLeak")
    private ImageView bmImage;
    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
        //System.out.println("Loading Image");
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        //System.out.println("Loading Image");
        Bitmap bmp = null;
        try {
            System.out.println(urldisplay);
            InputStream in = new java.net.URL(urldisplay).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            //Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmp;
    }
    protected void onPostExecute(final Bitmap result) {
        MainActivity.handler.post(new Runnable() {
            @Override
            public void run() {
                bmImage.setImageBitmap(result);
                PostFragment.adapter.notifyDataSetChanged();
                System.out.println("Image loadeedddddddd");
            }
        });

    }
}
