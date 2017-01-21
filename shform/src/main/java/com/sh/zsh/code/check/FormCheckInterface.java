package com.sh.zsh.code.check;

import android.view.View;

/**
 * Created by zhush on 2016/10/14.
 * E-mail zhush@jerei.com
 */
public interface FormCheckInterface {
    //自定表单验证
    public boolean formCheck(View v) ;
    //检查参数回掉
    public void formCheckParamCall(View v, String message);
    //检查参数为空回调
    public void formCheckNullCall(View v, String message);
}
