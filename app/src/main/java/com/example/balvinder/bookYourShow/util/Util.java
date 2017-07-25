package com.example.balvinder.bookYourShow.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;


/**
 * Created by surbhisandhu on 24/11/16.
 */

public class Util {
    public static void applyLetterSpacing(MyTextView pcTextView, String text, float letterspacing) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append(text.charAt(i));
            if (i + 1 < text.length()) {
                builder.append("\u00A0");
            }
        }

        SpannableString finalText = new SpannableString(builder.toString());
        float letterSpacingBuf = (letterspacing == -1) ? 0.1f : letterspacing + 1;
        if (builder.toString().length() > 1) {
            for (int i = 1; i < builder.toString().length(); i += 2) {
                finalText.setSpan(new ScaleXSpan(letterSpacingBuf / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        pcTextView.setText(finalText);

    }
}