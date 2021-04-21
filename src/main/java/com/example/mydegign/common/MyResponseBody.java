package com.example.mydegign.common;

public class MyResponseBody {
    private int code;
    private String description;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MyResponseBody() {
    }

    public MyResponseBody(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public MyResponseBody(int code, String description, Object data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
