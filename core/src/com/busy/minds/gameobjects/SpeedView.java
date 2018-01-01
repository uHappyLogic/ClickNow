package com.busy.minds.gameobjects;

/**
 * Created by Jan on 29.12.2017.
 */
public class SpeedView {

    public SpeedView(int speed){
        this.speed=speed-1000;
    }

    public String DrawSpeed(){

        return"Prędkość: "+speed;
    }
    int speed;
}
