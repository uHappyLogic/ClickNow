package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PointsCounter extends Actor {

    public PointsCounter(BitmapFont textBitmap){
        this.textBitmap = textBitmap;
        totalPoints = 0;
    }

    public void AddPoints(int count){
        totalPoints += count;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        textBitmap.draw(batch, GetPointsMessage(),320,380);
    }

    public void ResetCounter(){
        totalPoints = 0;
    }

    private String GetPointsMessage(){
        return String.format("You have earned %d", totalPoints);
    }

    int totalPoints;
    private BitmapFont textBitmap;
}
