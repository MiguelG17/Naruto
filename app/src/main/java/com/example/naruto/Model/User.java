package com.example.naruto.Model;

public class User {

    private String id;
    private String username;
    private String list;
    private String coin;

    public User(String id, String username, String list,String coin) {
        this.id = id;
        this.username = username;
        this.list = list;
        this.coin = coin;
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
