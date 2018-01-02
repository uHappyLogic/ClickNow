package com.busy.minds.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Timer {
   public Timer(
           int timeSeconds
           ,int timeMinutes
           , IGameManager gameManager)
   {
       this.timeMinutes=timeMinutes;
       this.seconds= timeSeconds;
       this.gameManager = gameManager;
   }

    public void TimerWork(float delta)
    {
        if ((timeMinutes!=0)||(seconds!=0)) {

            timeSeconds += delta;

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

            gameManager.ShowStartScreen();
        }
    }

    public String DrawTime(){
        return String.format("czas %02d:%02d ", timeMinutes, seconds);
    }

    int pickCounter;
    private double timeSeconds;
    private final IGameManager gameManager;
    private int timeMinutes;
    private int seconds;
}
