package com.example.yuzelli.bookkeepmananger.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/9/6.
 */

public class TypeBean implements Serializable{
    private int typeID;
    private String name ;
    private int typeRESID;

    public TypeBean(int typeID, String name, int typeRESID) {
        this.typeID = typeID;
        this.name = name;
        this.typeRESID = typeRESID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeRESID() {
        return typeRESID;
    }

    public void setTypeRESID(int typeRESID) {
        this.typeRESID = typeRESID;
    }
}
