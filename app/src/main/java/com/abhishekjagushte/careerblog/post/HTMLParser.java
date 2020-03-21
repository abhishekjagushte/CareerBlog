package com.abhishekjagushte.careerblog.post;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

public class HTMLParser implements Html.ImageGetter, Html.TagHandler {


    private String input;

    public HTMLParser(String input) {
        this.input = input;
    }

    @Override
    public Drawable getDrawable(String source) {

        return null;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

    }
}
