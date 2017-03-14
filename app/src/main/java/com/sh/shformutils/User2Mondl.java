package com.sh.shformutils;

import java.lang.ref.PhantomReference;

/**
 * Created by zhush on 2017/3/14.
 * E-mail 405086805@qq.com
 * PS
 */
public class User2Mondl {
    private String userNmae;
    private String userPhone;
    private boolean haveHouser;
    private String houseTime;
    private String  area;
    private double price;//单价
    private int pattern;//格局
    private int layer;//层数

    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        this.userNmae = userNmae;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }



    public String getHouseTime() {
        return houseTime;
    }

    public void setHouseTime(String houseTime) {
        this.houseTime = houseTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPattern() {
        return pattern;
    }

    public void setPattern(int pattern) {
        this.pattern = pattern;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean getHaveHouser() {
        return haveHouser;
    }

    public void setHaveHouser(boolean haveHouser) {
        this.haveHouser = haveHouser;
    }

    @Override
    public String toString() {
        return "User2Mondl{" +
                "userNmae='" + userNmae + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", haveHouser=" + haveHouser +
                ", houseTime='" + houseTime + '\'' +
                ", area='" + area + '\'' +
                ", price=" + price +
                ", pattern=" + pattern +
                ", layer=" + layer +
                '}';
    }
}
