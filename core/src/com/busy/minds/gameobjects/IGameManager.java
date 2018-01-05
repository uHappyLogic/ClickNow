package com.busy.minds.gameobjects;

/**
 * interface zakonczenia, rozpoczęcia gry
 * Created by Jan on 02.01.2018.
 */
public interface IGameManager {

    void StartNewGame();
    void ShowStartScreen();
    void PauseTheGame();
    void ResumeTheGame();

}
