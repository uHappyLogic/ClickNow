package com.busy.minds.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
/**klasa timera odliczającego czas*/

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
    /**metoda obsługująca mechanizm dzialania zegara*/
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
            //wykonanie metody która ma za zadanie zakonczyc gre oraz przypisac wynik do pliku
            gameManager.ShowStartScreen();
        }
    }
    //metoda zwracajca aktualny czas
    public String DrawTime(){
        return String.format("czas %02d:%02d ", timeMinutes, seconds);
    }

    /**czas rzeczywisty*/
    private double timeSeconds;
    /**interfejs IGameManager*/
    private final IGameManager gameManager;
    /**czas w minutach*/
    private int timeMinutes;
    /**czas w sekundach*/
    private int seconds;
}
