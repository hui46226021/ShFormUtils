package com.sh.zsh.code.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhush on 2016/10/12.
 * E-mail zhush@jerei.com
 *
 * isPhoneNum   手机号
 * isEmail      邮箱
 * is6To12      密码强度 是不是6至12位字母组合
 * isChinese    是不是中文
 * isIdCard     身份证
 * isData       “yyyy-mm-dd“ 格式的日期校验，已考虑平闰年
 * isAmountMoney  金额校验，精确到2位小数
 * isURL         URL链接
 */
public class RoutineVerification {


    //手机号
    static final String PHONE = "^1[3|4|5|7|8][0-9]{9}$";
    //邮箱
    static final String EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    //中文
    static final String CHINESE = "^[\\u4e00-\\u9fa5]{0,}$";
    //15位身份证
    static final String ID_CODE_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    //18位身份证
    static final String ID_CODE_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
    //日期
    static final String STANDARD_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    //金额校验，精确到2位小数
    static final String AMOUNTMONEY = "^[0-9]+(.[0-9]{2})?$";
    //纯数字
    static final String AMOUNT = "^[0-9]*$";
    //URL
    static final String URL = "^(f|ht){1}(tp|tps):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?";



    /**
     * 是否位手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhoneNum(String str) {
        return verification(str, PHONE);
    }

    /**
     * 是否是邮箱
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        return verification(str, EMAIL);
    }

    /**
     * 判断字符串是不是6至12位字母组合
     *
     * @param str
     * @return
     */
    public static boolean is6To12(String str) {
        Pattern pat = Pattern.compile("[\\da-zA-Z]{6,12}");
        Pattern patno = Pattern.compile(".*\\d.*");
        Pattern paten = Pattern.compile(".*[a-zA-Z].*");
        Matcher mat = pat.matcher(str);
        Matcher matno = patno.matcher(str);
        Matcher maten = paten.matcher(str);
        if (matno.matches() && maten.matches() && mat.matches()) {

            Pattern pattern3 = Pattern.compile("^(\\d+)(.*)");
            Matcher matcher3 = pattern3.matcher(str);
            if (!matcher3.matches()) {//数字开头

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }


    /**
     * 是不是中文
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        return verification(str, CHINESE);
    }

    /**
     * 是不是身份证
     *
     * @param str
     * @return
     */
    public static boolean isIdCard(String str) {
        if (verification(str, ID_CODE_15) || verification(str, ID_CODE_18)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * “yyyy-mm-dd“ 格式的日期校验，已考虑平闰年
     * @param str
     * @return
     */
    public static boolean isData(String str) {
        return verification(str, STANDARD_DATE);
    }

    /**
     * 金额校验，精确到2位小数
     * @param str
     * @return
     */
    public static boolean isAmountMoney(String str) {
        return verification(str, AMOUNTMONEY);
    }
    /**
     * 金额校验，精确到2位小数
     * @param str
     * @return
     */
    public static boolean isAmount(String str) {
        return verification(str, AMOUNT);
    }

    /**
     * 提取URL链接
     * @param str
     * @return
     */
    public static boolean isURL(String str) {
      if(str.startsWith("https://")||str.startsWith("http://")){
            return true;
      }else {
          return false;
      }
    }


    /**
     * 验证
     *
     * @param str
     * @param roution
     * @return
     */
    public static boolean verification(String str, String roution) {
        Pattern pattern = Pattern.compile(roution);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {//
            return false;
        } else {
            return true;
        }
    }
}
