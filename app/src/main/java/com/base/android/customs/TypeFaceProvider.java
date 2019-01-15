package com.base.android.customs;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by NguyenLinh on 15,January,2019
 */
class TypeFaceProvider {

    private static Hashtable<Integer, Typeface> sTypeFaces = new Hashtable<>(
            4);

    static Typeface getTypeFace(Context context, int textStyle) {
        Typeface tempTypeface = sTypeFaces.get(textStyle);

        if (tempTypeface == null) {
            tempTypeface = getTypefaceFromAsset(context, textStyle);
            sTypeFaces.put(textStyle, tempTypeface);
        }

        return tempTypeface;
    }

    private static Typeface getTypefaceFromAsset(Context context, int textStyle) {
        /*
         * information about the TextView textStyle:
         * http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
         */
        switch (textStyle) {
            case Typeface.BOLD: // bold
                return getTypefaceFont(context, "fonts/Lato-Bold.ttf");
            case Typeface.ITALIC: // bold
                return getTypefaceFont(context, "fonts/Lato-Italic.ttf");
            default:
                return getTypefaceFont(context, "fonts/Lato.ttf");
        }
    }

    static Typeface getTypefaceFont(Context context, String fontName) {
        return Typeface.createFromAsset(context.getAssets(), fontName);
    }
}
