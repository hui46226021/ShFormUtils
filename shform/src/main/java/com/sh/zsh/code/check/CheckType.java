package com.sh.zsh.code.check;

/**
 * Created by zhush on 2016/10/12.
 * E-mail zhush@jerei.com
 */
public enum CheckType {
    CUSTOM,//自定义
    PHONE,//手机号
    EMAIL,//邮箱
    CHINESE,//中文
    IDCARD,//身份证
    ISDATA,//“yyyy-mm-dd“ 格式的日期校验，已考虑平闰年
    AMOUNT_MONEY,//金额校验，精确到2位小数
    AMOUNT,//存数字
    URL,//Url
    PASSWORD;//密码强度 是不是6至12位字母组合

}
