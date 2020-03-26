package kasei.web;

import okhttp3.OkHttpClient;

public enum OkHttpSingleton {

    SINGLETON;

    private OkHttpClient instance;


    private OkHttpSingleton(){
        instance = new OkHttpClient();
    }

    public OkHttpClient getInstance(){
        return instance;
    }







}
