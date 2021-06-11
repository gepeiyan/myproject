package com.example.first;

public class RateItem {
    private int id;
    private  String curName;
    private  String curRate;
    public RateItem(){
        super();
        curName="";
        curRate="";

    }
    public RateItem(String curName,String curRate){
        super();
        this.curRate=curRate;
        this.curName=curName;

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getCurRate(){
        return curRate;
    }
    public void setCurName(){
        this.curName=curName;
    }
    public void setCurRate(){
        this.curRate=curRate;
    }
    public String getCurName(){
        return curName;
    }

}
