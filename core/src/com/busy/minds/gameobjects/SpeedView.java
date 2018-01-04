package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * klasa prędkości osi pionowej
 * Created by Jan on 29.12.2017.
 */
public class SpeedView extends Actor{

    public SpeedView(BitmapFont bitmap, VerticalAxis verticalAxis){

        this.verticalAxis=verticalAxis;
        this.bitmap=bitmap;

    }
    /**nadpisana metoda pozwala na wyswietlenie predkosci osi pionowej */
    @Override
    public void draw(Batch batch, float parentAlpha){

        bitmap.draw(batch, "Your speed: " + verticalAxis.speed,100,100);
    }

    BitmapFont bitmap;
    VerticalAxis verticalAxis;
}
