package com.sh.zsh.code.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zhush on 2017/3/12.
 */

public class ShFormLayout extends LinearLayout{

    public ShFormLayout(Context context) {
        super(context);
        super.setOrientation(LinearLayout.VERTICAL);
    }

    public ShFormLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {

        super.addView(child, params);
    }
}
