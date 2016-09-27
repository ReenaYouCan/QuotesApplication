/**
 *
 */
package com.reamu.yourquotes.views.custom_control;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.reamu.yourquotes.R;


/**
 * Defines Custom text view with specific font
 *
 * @author Reena
 * @created 4 Jun, 2016
 */
public class CustomTextView extends TextView {

    public CustomTextView(final Context context, final AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        setFont(context, attributeSet);
    }

    public CustomTextView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
        setFont(context, attributeSet);
    }

    public CustomTextView(final Context context) {
        super(context);
    }

    private void setFont(final Context context, final AttributeSet attrs) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.Font, 0, 0);
        String fontName;
        try {
            fontName = a.getString(R.styleable.Font_fontName);
        } finally {
            a.recycle();
        }
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/" + fontName);
        setTypeface(tf);
    }
}
