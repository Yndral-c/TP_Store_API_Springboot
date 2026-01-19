package com.example.TPStoreAPI.bo;

public class ResponseCode<T> {
    public String code;
    public String message;
    public T data;

    public ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseCode(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
