package com.base.android.customs;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by nguyenvanlinh on 6/20/18.
 * Project: BaseProject
 */
public class CustomButton extends AppCompatButton {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomButton(Context context) {
        super(context);
        if (!isInEditMode()) {
            setTypeface(TypeFaceProvider.getTypefaceFont(context,"fonts/Lato.ttf"));
        }
    }
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context,attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context,attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
            Typeface customFont = TypeFaceProvider.getTypeFace(context, textStyle);
            setTypeface(customFont);
        }
    }

}