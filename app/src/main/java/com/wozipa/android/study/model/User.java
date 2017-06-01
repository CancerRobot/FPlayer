package com.wozipa.android.study.model;

/**
 * Created by xinglinjie on 2017/6/1.
 */

public class User {
    public User(){}

    public User(int award,int punish){
        this.award = award;
        this.punish = punish;
    }

    private int id;
    private int award;
    private int punish;

    public int getId() {
        return id;
    }

    public int getAward() {
        return award;
    }

    public int getPunish() {
        return punish;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public void setPunish(int punish) {
        this.punish = punish;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", award=" + award +
                ", punish=" + punish +
                '}';
    }
}
