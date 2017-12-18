package com.busy.minds.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Timer{
   public Timer(double timeSeconds,int timeMinutes)
   {
       this.timeMinutes=timeMinutes;
       this.timeSeconds=timeSeconds;

   }
    public void TimerWork()
    {

        if ((timeMinutes!=0)||(seconds!=0)) {

            timeSeconds += Gdx.graphics.getDeltaTime();
            //timeSeconds = Math.round(timeSeconds * 100) / 100.0;

//            if (timeSeconds >= 1) {
//                seconds+=1;
//               timeSeconds = 0;                        //
//            }
//            if (timeSeconds>=60) {
//
//                timeMinutes += 1;
//                timeSeconds=0;
//            }


            if (timeSeconds >= 1) {
                seconds-=1;
                timeSeconds = 0;
            }
            if (seconds <= -1) {
                seconds = 59;
                timeMinutes -= 1;
            }
        }
        else
        {
            timeMinutes=0;
            timeSeconds=0;
            // TODO zakonczenie gry ustawienie klawiza restartu gry

        }
    }

    public String DrawTime(){
        return String.format("czas %02d:%02d ", timeMinutes, seconds);
    }

    public String SpaceHitCounter(){

        return "liczba punktów za trafienie w spacje "+pickCounter+" razy";
    }
    int pickCounter;
    private double timeSeconds;
    private int timeMinutes;
    private int seconds;
    //moze zastasowac if(hitblock.overlops(verticalaxis)) nakładaja sie

}
