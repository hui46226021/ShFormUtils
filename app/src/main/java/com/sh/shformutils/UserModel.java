package com.sh.shformutils;

import java.io.Serializable;

/**
 * Created by zhush on 2017/1/10.
 * E-mail zhush@jerei.com
 * PS
 */
public class UserModel implements Serializable{
    String name;
    String profession;//职业
    String phone;//电话
    String workingLife;//工作时间
    Boolean married;//已婚
    Boolean party;//是否是党员

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(String workingLife) {
        this.workingLife = workingLife;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public Boolean getParty() {
        return party;
    }

    public void setParty(Boolean party) {
        this.party = party;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", phone='" + phone + '\'' +
                ", workingLife='" + workingLife + '\'' +
                ", married=" + married +
                ", party=" + party +
                '}';
    }
}
