package com.wozipa.android.study.model;

/**
 * Created by Wozipa on 2017/5/21.
 */

public class Action {

    public Action(){}

    public Action(String name,String content,String start,String end,int record)
    {
        this.name=name;
        this.content=content;
        this.start=start;
        this.end=end;
        this.record=record;
    }


    private int id;
    private String name;
    private String content;
    private String start;
    private String end;
    private int record;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "["+id+","+name+","+content+","+start+","+end+","+record+"]";
    }
}
