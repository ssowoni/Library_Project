package com.wish.library.member.domain;

public enum Auth {
    A("ADMIN"),M("MEMBER");

    final private String auth;

    public String getAuth(){
        return auth;
    }

    Auth(String auth) {
        this.auth = auth;
    }

}
