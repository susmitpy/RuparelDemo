package com.example.rupareldemo;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Notice implements Serializable {
    String title;
    Timestamp ts;

    public Notice(){}

    public Notice(String title){
        this.title = title;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("title", this.title);

        return map;
    }

    public String getTitle() {
        return title;
    }
}
