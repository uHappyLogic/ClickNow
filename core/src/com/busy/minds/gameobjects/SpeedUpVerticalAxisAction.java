package com.busy.minds.gameobjects;

/**
 * Created by Jan on 18.12.2017.
 */
public class SpeedUpVerticalAxisAction implements IGameAction {

    public SpeedUpVerticalAxisAction(VerticalAxis verticalAxis){
        this.verticalAxis = verticalAxis;
    }

    @Override
    public void Execute() {
        verticalAxis.SpeedUp(3000);
    }

    private VerticalAxis verticalAxis;
}
