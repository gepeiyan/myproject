package com.example.first;

public class Rateite {
    private String cname;
    private String cval;
    public Rateite(String cname, String cval){
        this.cname=cname;
        this.cval=cval;
    }
    public String getCame(){
        return cname;
    }
    public void setCname(String cname){
        this.cname=cname;
    }
    public String getCval(){
        return cval;
    }
    public void setCval(String cval){
        this.cval=cval;
    }

}
