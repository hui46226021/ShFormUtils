package com.sh.shformutils.form;

import android.view.View;

/**
 * Created by zhush on 2016/10/8.
 * E-mail zhush@jerei.com
 */
public class ViewAttribute {

    public View view;
    private boolean isNull; //是否爲空
    private String message ; //提示消息
    private CheckType checkType; //检查类型
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }
}
