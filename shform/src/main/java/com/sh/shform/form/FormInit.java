package com.sh.shform.form;

import android.text.TextUtils;
import android.view.View;

import com.sh.shform.annotation.FormInjection;
import com.sh.shform.check.CheckType;
import com.sh.shform.annotation.FormCheck;
import com.sh.shform.check.ViewAttribute;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by zhush on 2016/10/8.
 * E-mail zhush@jerei.com
 */
public class FormInit {


    /**
     * 装有所有控件全局map
     * 第一层 key是当前页面
     * 第二层 key是当前字段
     */
    public static HashMap<String, HashMap<String, ViewAttribute>> allLineFormViewMap = new HashMap<>();

    /**
     * 初始化表单注入  要在 所有控件初始化成功后 调用
     * @param page
     */
    public static void  injection(Object page){
        Class clazz = page.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // 遍历所有成员变量
        for (Field field : fields) {
            FormInjection viewInjectAnnotation = field
                    .getAnnotation(FormInjection.class);
            if (viewInjectAnnotation != null)
            {
                String name =viewInjectAnnotation.name();
                String message =viewInjectAnnotation.message();
                boolean isNull =viewInjectAnnotation.isNull();
                if(!TextUtils.isEmpty(name)){
                    View v = null;
                    boolean access = field.isAccessible();
                    if(!access) field.setAccessible(true);
                    ViewAttribute va = new ViewAttribute();
                    //查看是否有驗證
                    FormCheck formCheck = field
                            .getAnnotation(FormCheck.class);
                    CheckType checkType = null;
                    if(formCheck !=null){
                        checkType=  formCheck.type();
                    }


                    try {
                        v =   (View) field.get(page);
                        va.setView(v);
                        va.setNull(isNull);
                        va.setMessage(message);
                        va.setCheckType(checkType);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }



                    sevaLineForm(page.getClass().getName(),name,va);
                }
            }
        }
    }

    public static synchronized void sevaLineForm(String pageNmae,String name,ViewAttribute v) {
        HashMap<String, ViewAttribute> map = null;
        if (allLineFormViewMap.get(pageNmae) == null) {
            map = new HashMap<>();
            allLineFormViewMap.put(pageNmae, map);
        } else {
            map = allLineFormViewMap.get(pageNmae);
        }
        if (map.get(name) != null) {
            throw new RuntimeException("重复设置 name>>>>" + name);
        } else {
            map.put(name, v);
        }

    }


    /**
     * 页面销毁时 删除当前页面的表单控件
     * @param page
     */
    public static void  deleteInjection(Object page){
        allLineFormViewMap.remove(page.getClass().getName());
    }
}
