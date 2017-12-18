package com.busy.minds.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Axis extends Actor {

    public Axis(Vector2 begin, Vector2 end, PointsCounter pointsCounter){
        this.pointsCounter = pointsCounter;
        this.beginOfAxis = begin;
        this.endOfAxis = end;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rectLine(beginOfAxis, endOfAxis, 5);
        shapeRenderer.end();

        for (HitBlock currentHitBlock : hitBlocks) {
            currentHitBlock.draw(batch,parentAlpha);
        }

        if (verticalAxis != null)
            verticalAxis.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (verticalAxis != null)
            verticalAxis.act(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            spaceHit();
        }
    }

    private void spaceHit(){

        for(HitBlock currentHitBlock : hitBlocks){
            Rectangle r = new Rectangle(
                    currentHitBlock.getX(),
                    currentHitBlock.getY() - currentHitBlock.getHeight() / 2,
                    currentHitBlock.getWidth(),
                    currentHitBlock.getHeight()
                    );

            if (r.contains(new Vector2(verticalAxis.getX(), verticalAxis.getY()))){
                for (IGameAction a : currentHitBlock.getGameActions())
                    a.Execute();
            }
        }
    }

    private Vector2 GetRandomPointOnAxis(float offset){
        Random r = new Random();
        return new Vector2(beginOfAxis).add(new Vector2((endOfAxis.x - this.beginOfAxis.x - offset) * r.nextFloat() , 0));
    }

    public void addHitBlock(){
        Random r = new Random();
            // TODO study this shit
        float width = r.nextFloat() * (50f - 20f) + 20f;

        List<IGameAction> hitBlockGameActions = new ArrayList<IGameAction>();
        hitBlockGameActions.add(new AddPointsAction(pointsCounter, 100));
        hitBlockGameActions.add(new SpeedUpVerticalAxisAction(verticalAxis));

        HitBlock hitBlock = new HitBlock(GetRandomPointOnAxis(width)
                , width
                , hitBlockGameActions
        );

        hitBlocks.add(hitBlock);
    }

    public void AddVertcalAxis(){
        float width = 10;
        this.verticalAxis = new VerticalAxis(GetRandomPointOnAxis(width), width);
        verticalAxis.SetAxis(this);
    }

    public float GetLeftBorder() {
        return beginOfAxis.x;
    }

    public float GetRightBorder() {
        return endOfAxis.x;
    }

    Vector2 beginOfAxis;
    Vector2 endOfAxis;
    VerticalAxis verticalAxis;
    List<HitBlock> hitBlocks = new ArrayList<HitBlock>();
    private PointsCounter pointsCounter;
}
