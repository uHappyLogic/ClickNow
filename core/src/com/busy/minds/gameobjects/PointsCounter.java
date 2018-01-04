package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
/**klasa licząca zdobyte punkty*/
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
    /**metoda dodajaca punkty*/
    public void AddPoints(int count){
        totalPoints += count;
    }
    /**metoda odejująca pnty*/
    public void SetPoints(int count){
        totalPoints = count;
    }
    /**metoda zwracająca liczbę zdobytych punktów*/
    public int GetPoints() {
        return  totalPoints;
    }
    /**nadpisujemy metodę umożliwiającą wypisanie w oknie liczbę zdobytych punktów*/
    @Override
    public void draw(Batch batch, float parentAlpha) {
        textBitmap.draw(batch, GetPointsMessage(),position.x, position.y);
    }

    private String GetPointsMessage(){
        return String.format(formattedText, totalPoints);
    }
    /**zdobyte punkty*/
    int totalPoints;
    private BitmapFont textBitmap;
    /**lokalizacja wypisanego wyniku w oknie*/
    Vector2 position;
    /**sformatowany, wypisany tekst*/
    private final String formattedText;
}
