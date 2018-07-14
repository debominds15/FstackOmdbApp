package com.demo.fstack.fstackapp.util.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Hashtable;

public class TextViewBold extends AppCompatTextView {

    private Context context;
    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    public TextViewBold(Context context) {
        super(context);
        this.context = context;
        setCustomFont(context);
    }

    public TextViewBold(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public TextViewBold(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context);
    }

    private void setCustomFont(Context conxt) {
        Typeface tf = null;
        try {
            tf = get("Montserrat-Bold.ttf", conxt);
            setTypeface(tf, Typeface.NORMAL);
        } catch (Exception e) {
            Log.e("TextViewBold", "" + e.getMessage());
        }
    }

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}
