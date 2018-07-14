package com.demo.fstack.fstackapp.util.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Hashtable;

public class TextViewRegular extends AppCompatTextView {

    private Context context;
    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    public TextViewRegular(Context context) {
        super(context);
        this.context = context;
        setCustomFont(context);
    }

    public TextViewRegular(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public TextViewRegular(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context);
    }

    private void setCustomFont(Context conxt) {
        Typeface tf = null;
        try {
            tf = get("Montserrat-Regular.ttf", conxt);
            setTypeface(tf, Typeface.NORMAL);
        } catch (Exception e) {
            Log.e("TextViewRegular", "" + e.getMessage());
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
