package com.example.eng;

public class ExampleItem {

    private String text1, text2,numer;
    public ExampleItem(String elo,String elo2,String num)
    {
        text1 =elo;
        text2 = elo2;
        numer =num;
    }
    public String getText1(){
        return text1;
    }
    public String getText2 (){
        return text2;
    }
    public String getNumer() { return numer; }
}

