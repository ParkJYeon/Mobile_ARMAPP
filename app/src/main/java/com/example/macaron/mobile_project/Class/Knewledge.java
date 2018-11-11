package com.example.macaron.mobile_project.Class;

public class Knewledge {
    String title;
    String content;

    public Knewledge(){

    }

    public Knewledge(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String gettitle(){
        return title;
    }

    public String getcontent(){
        return content;
    }
}
