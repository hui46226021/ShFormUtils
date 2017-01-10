package com.sh.shfrom.check;

import android.view.View;

/**
 * Created by zhush on 2016/10/14.
 * E-mail zhush@jerei.com
 */
public interface FromCheckInterface {
    //自定表单验证
    public boolean fromCheck(View v) ;
    //检查参数回掉
    public void fromCheckParamCall(View v, String message);
    //检查参数为空回调
    public void fromCheckNullCall(View v, String message);
}
