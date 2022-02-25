package com.base.android.customs;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by nguyenvanlinh on 6/20/18.
 * Project: BaseProject
 */
public class CustomEditText extends AppCompatEditText {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomEditText(Context context) {
        super(context);
        if (!isInEditMode()) {
            setTypeface(TypeFaceProvider.getTypefaceFont(context,"fonts/Lato.ttf"));
        }
    }
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context,attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
