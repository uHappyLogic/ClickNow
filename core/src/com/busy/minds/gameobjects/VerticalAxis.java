package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class VerticalAxis extends Actor {

    public VerticalAxis(
        Vector2 position
        , float width
        , IBordersProvider bordersProvider
    ){
        setX(position.x);
        setY(position.y);
        setWidth(width);
        setHeight(120);

        this.bordersProvider = bordersProvider;
        speed = 1000;
        jeden = 1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 1, 1);
        shapeRenderer.rect(getX(), getY() - getHeight() / 2, getWidth(), getHeight());
        shapeRenderer.end();


    }
    @Override
    public void act(float delta) {
        super.act(delta);
        setX((getX()+speed * delta * jeden));
        if(getX() + getWidth() / 2 >= bordersProvider.GetRightBorder()){
          jeden=-1;
        }
        else
        if(getX() - getWidth() / 2 <= bordersProvider.GetLeftBorder()){
            jeden=1;
        }
    }

    public void ChangeSpeed(float speed){
        this.speed += speed;
    }

    private IBordersProvider bordersProvider;
    private float speed;
    private int jeden;
}
