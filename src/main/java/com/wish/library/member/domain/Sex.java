package com.wish.library.member.domain;

public enum Sex {
    M("men"), F("female");

    final private String sex;
    public String getSex(){
        return sex;
    }

    Sex(String sex){
        this.sex = sex;
    }
}
