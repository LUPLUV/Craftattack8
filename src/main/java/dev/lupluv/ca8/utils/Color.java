package dev.lupluv.ca8.utils;

public enum Color {

    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    PINK;

    public String toString(){
        if(this == RED){
            return "§4";
        }
        if(this == ORANGE){
            return "§6";
        }
        if(this == YELLOW){
            return "§2";
        }
        if(this == GREEN){
            return "§3";
        }
        if(this == BLUE){
            return "§9";
        }
        if(this == PINK){
            return "§d";
        }
        return null;
    }

}
