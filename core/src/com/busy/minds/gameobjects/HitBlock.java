package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;


public class HitBlock extends Actor {

    public HitBlock(Vector2 position, float width, List<IGameAction> gameAction){

        setX(position.x);
        setY(position.y);
        setWidth(width);
        setHeight(50);

        jeden = 1;
        this.gameActions = gameAction;
    }

    int jeden;
    @Override
    public void draw(Batch batch, float parentAlpha) {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(getX(), getY() - getHeight() / 2, getWidth(), getHeight());
        shapeRenderer.end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        setX((getX()+speed*delta*jeden));
//
//        if(getX()>=border1){
//            jeden=-1;
//        }
//        else
//        if(getX()<=border2){
//            jeden=1;
//
//        }
    }

    public List<IGameAction> getGameActions() {
        return gameActions;
    }

    private List<IGameAction> gameActions;
}
