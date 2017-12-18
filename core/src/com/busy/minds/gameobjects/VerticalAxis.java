package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class VerticalAxis extends Actor {

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
        if(getX()>= axis.GetRightBorder()){
          jeden=-1;
        }
        else
        if(getX()<= axis.GetLeftBorder()){
            jeden=1;

        }
    }

    public void SetAxis(Axis axis){
        this.axis = axis;
    }

    public VerticalAxis(Vector2 position, float width){

        setX(position.x);
        setY(position.y);
        setWidth(width);
        setHeight(120);

        speed = 1000;
        jeden = 1;
    }

    public void SpeedUp(float speed){
        this.speed += speed;
    }

    Axis axis;
    float speed;
    int jeden;
}
