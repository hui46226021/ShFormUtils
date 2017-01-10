package com.sh.shfrom.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhush on 2016/10/12.
 * E-mail zhush@jerei.com
 */
public class RoutineVerification {
    /**
     * 是否位手机号
     * @param str
     * @return
     */
    public static boolean isPhoneNum (String str){
        Pattern pattern = Pattern.compile("^1[3|4|5|7|8][0-9]{9}$");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {//
            return false;
        }else {
            return true;
        }

    }

    /**
     * 是否是邮箱
     * @param str
     * @return
     */
    public static boolean isEmail (String str){
        if(str.contains("@")){
            return true;
        }
        return false;

    }

    /**
     * 判断字符串是不是6至12位字母组合
     * @param str
     * @return
     */
    public static boolean is6To12 (String str){
        Pattern pat = Pattern.compile("[\\da-zA-Z]{6,12}");
        Pattern patno = Pattern.compile(".*\\d.*");
        Pattern paten = Pattern.compile(".*[a-zA-Z].*");
        Matcher mat = pat.matcher(str);
        Matcher matno = patno.matcher(str);
        Matcher maten = paten.matcher(str);
        if(matno.matches()&& maten.matches() && mat.matches()){

            Pattern pattern3 = Pattern.compile("^(\\d+)(.*)");
            Matcher matcher3 = pattern3.matcher(str);
            if (!matcher3.matches()) {//数字开头

                return true;
            }else {
                return false;
            }

        }else {
            return false;
        }
    }
}
