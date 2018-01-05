package com.busy.minds.gameobjects;

/**
 * klasa akcji dodawania punktów do tych juz uzyskanych
 * Created by Jan on 10.12.2017.
 */
public class AddPointsAction implements IGameAction {

    public AddPointsAction(PointsCounter pointsCounter, int points){
        this.pointsCounter = pointsCounter;
        this.points = points;
    }
    /**metoda dodająca punkty*/
    @Override
    public void Execute() {
        pointsCounter.AddPoints(points);
    }
    /**obiekt  PointCounter*/
    PointsCounter pointsCounter;
    /**liczba punktów*/
    int points;
}
