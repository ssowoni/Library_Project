package com.wish.library.member.domain;

public enum Auth {
    A("admin"),M("member");

    final private String auth;

    public String getAuth(){
        return auth;
    }

    Auth(String auth) {
        this.auth = auth;
    }

}
