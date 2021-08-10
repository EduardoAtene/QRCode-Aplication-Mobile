package com.example.testeestagio.model;

import android.util.Log;

import java.util.ArrayList;

public class PokemonDados {
    private String id;
    private String name;
    private ArrayList<String> type = new ArrayList<>();
    private String front_default_img;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTypes(){
        return this.type;
    }

    public String getType(int index){
        try{
            return type.get(index);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void setTipo(String tipo) {
        tipo = tipo.toLowerCase();
        tipo = tipo.substring(0,1).toUpperCase() + tipo.substring(1).toLowerCase();
        this.type.add(tipo);
    }

    public String getFront_default_img() {
        return front_default_img;
    }

    public void setFront_default_img(String front_default_img) {
        this.front_default_img = front_default_img;
    }
}
