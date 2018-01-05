package com.busy.minds.gameobjects;

/**
 * klasa zmiany prędkości
 * Created by Jan on 18.12.2017.
 */
public class ChangeVerticalAxisAction implements IGameAction {

    public ChangeVerticalAxisAction(
            VerticalAxis verticalAxis
            , int deltaSpeed
    ){
        this.verticalAxis = verticalAxis;
        this.deltaSpeed = deltaSpeed;
    }
    /**nadpisanie metody umozliwiającj zwiekszenie prędkości z naka porusza nię oś pionowa*/
    @Override
    public void Execute() {
        verticalAxis.ChangeSpeed(deltaSpeed);
    }
    /**obiekt klasy VerticalAxis*/
    private VerticalAxis verticalAxis;
    /**zmiana prędkości*/
    private final int deltaSpeed;
}
