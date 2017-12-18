package com.busy.minds.gameobjects;

/**
 * Created by Jan on 10.12.2017.
 */
public class AddPointsAction implements IGameAction {

    public AddPointsAction(PointsCounter pointsCounter, int points){
        this.pointsCounter = pointsCounter;
        this.points = points;
    }

    @Override
    public void Execute() {
        pointsCounter.AddPoints(points);
    }

    PointsCounter pointsCounter;
    int points;
}
