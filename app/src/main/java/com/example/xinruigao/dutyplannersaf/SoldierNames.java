package com.example.xinruigao.dutyplannersaf;

public class SoldierNames {
    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SoldierNames(String name) {
        this.name = name;
    }

    public SoldierNames(int id, String name) {
        this.id = id;
        this.name = name;
    }



}
