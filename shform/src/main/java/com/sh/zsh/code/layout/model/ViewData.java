package com.sh.zsh.code.layout.model;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by zhush on 2017/1/19.
 * E-mail zhush@jerei.com
 * PS
 */
public class ViewData implements IPickerViewData {
    String key;
    Object value;
    Object valueObject;
    public ViewData(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public ViewData(String key, Object value, Object valueObject) {
        this.key = key;
        this.value = value;
        this.valueObject = valueObject;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String getPickerViewText() {
        return key;
    }

    public Object getValueObject() {
        return valueObject;
    }

    public void setValueObject(Object valueObject) {
        this.valueObject = valueObject;
    }
}
