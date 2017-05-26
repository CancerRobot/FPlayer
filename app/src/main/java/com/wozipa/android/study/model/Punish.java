package com.wozipa.android.study.model;

/**
 * Created by xinglinjie on 2017/5/26.
 */

public class Punish {

    public Punish(){};

    public Punish(String name, int cost, String content){
        this.name=name;
        this.cost=cost;
        this.content=content;
    }

    private int id;
    private String name;
    private int cost;
    private String content;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Punish{" +
                "id=" + id +
                ", name='" + name +
                ", cost=" + cost +
                ", content='" + content +
                '}';
    }
}
