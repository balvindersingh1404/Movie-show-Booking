package com.example.balvinder.bookYourShow.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import android.util.AttributeSet;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;



public class MyTextView extends TextView {
    private CharSequence originalText = "";
    private boolean mNegativeLineSpacing = false;
    public float letterspacing = 0; // normal
    boolean applyTextCalled;

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    public void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyTextView_typeface:
                    String customFont = a.getString(R.styleable.MyTextView_typeface);
                    setCustomFont(ctx, customFont);
                    break;

                case R.styleable.MyTextView_letterspacing:
                    letterspacing = a.getFloat(R.styleable.MyTextView_letterspacing, 0);
//                     applyLetterSpacing();
                    break;

                case R.styleable.MyTextView_text:
                    originalText = a.getString(R.styleable.MyTextView_text);
                    //applyLetterSpacing();
                    break;
            }
        }
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            return false;
        }
        setTypeface(tf);
        return true;
    }


    //    /* line spacing */
    @Override
    public void setLineSpacing(float add, float mult) {
        mNegativeLineSpacing = add < 0 || mult < 1;
        super.setLineSpacing(add, mult);
    }
}
