package com.busy.minds.gameobjects;

/**
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

    @Override
    public void Execute() {
        verticalAxis.ChangeSpeed(deltaSpeed);
    }

    private VerticalAxis verticalAxis;
    private final int deltaSpeed;
}
