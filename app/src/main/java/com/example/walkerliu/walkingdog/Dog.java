package com.example.walkerliu.walkingdog;
import java.util.Date;
/**
 * Created by WalkerLiu on 2015/7/23.
 */
public class Dog {
    private String name;
    private int step;
    private int size;
    //private Date date;
    public Dog(String Name,int Step,int Size/*,Date date*/){
        this.name = Name;
        this.step = Step;
        this.size = Size;
        //    this.date = date;
    }
    public String getName(){return name;}
    public int getStep(){return step;}
    public int getSize(){return size;}
    //public Date getDate(){return date;}

    public void setSender(String Name){this.name = Name;}
    public void setStep(int Step){this.step = Step;}
    public void setSize(int Size){this.size = Size;}
    //public void setDate(Date date){this.date = date;}
}

