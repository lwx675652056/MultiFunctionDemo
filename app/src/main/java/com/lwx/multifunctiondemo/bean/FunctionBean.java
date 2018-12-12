package com.lwx.multifunctiondemo.bean;

/**
 * Created by Dell on 2018/12/12.
 */

public class FunctionBean {
    private String title;
    private String describe;

    public FunctionBean(String title, String describe) {
        this.title = title;
        this.describe = describe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
