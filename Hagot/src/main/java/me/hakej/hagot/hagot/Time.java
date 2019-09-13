package me.hakej.hagot.hagot;

public class Time {

    Hagot hagot;

    public Time(Hagot hagot) {
        this.hagot = hagot;
    }

    public void getTime() {
        System.out.println(hagot.timeValue);
    }
}
