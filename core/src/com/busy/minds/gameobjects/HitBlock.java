package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class HitBlock extends Actor {

    public HitBlock(
            Vector2 position
            , float width
            , Color color
            , IBordersProvider bordersProvider
    ){
        this.bordersProvider = bordersProvider;
        setX(position.x);
        setY(position.y);
        setWidth(width);
        setHeight(50);

        this.color = color;
        this.gameActions = new ArrayList<>();
        Random random=new Random();

        if(random.nextFloat()<=0.5f){
            jeden = -1;
        }else jeden = 1;

        if (random.nextFloat()<=0.3f){
        speed = random.nextFloat()*100+10;}
        else speed = random.nextFloat()*100;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
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


    public void AddGameAction(IGameAction gameAction) {
        gameActions.add(gameAction);
    }

    public List<IGameAction> getGameActions() {
        return gameActions;
    }

    private List<IGameAction> gameActions;
    private Axis axis;
    private float speed;
    private int jeden;
    private Color color;
    private IBordersProvider bordersProvider;

}
