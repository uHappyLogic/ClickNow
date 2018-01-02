package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PointsCounter extends Actor {

    public PointsCounter(
            BitmapFont textBitmap
            , Vector2 position
            , String formattedText
    ){
        this.textBitmap = textBitmap;
        this.position = position;
        this.formattedText = formattedText;
        totalPoints = 0;
    }

    public void AddPoints(int count){
        totalPoints += count;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        textBitmap.draw(batch, GetPointsMessage(),position.x, position.y);
    }

    public void ResetCounter(){
        totalPoints = 0;
    }

    private String GetPointsMessage(){
        return String.format(formattedText, totalPoints);
    }

    int totalPoints;
    private BitmapFont textBitmap;
    Vector2 position;
    private final String formattedText;
}
