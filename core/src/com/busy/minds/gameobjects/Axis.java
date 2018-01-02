package com.busy.minds.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Axis extends Actor implements IBordersProvider, IBlockManager {

    public Axis(Vector2 begin, Vector2 end, PointsCounter pointsCounter){
        this.pointsCounter = pointsCounter;
        this.beginOfAxis = begin;
        this.endOfAxis = end;
    }

    @Override
    public void RemoveBlock(HitBlock hitBlock){
        hitBlocksToRemove.add(hitBlock);
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

        // todo check this null
        if (verticalAxis != null)
            verticalAxis.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (verticalAxis != null)
            verticalAxis.act(delta);

        for (HitBlock hitBlock : hitBlocks)
            hitBlock.act(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            spaceHit();
        }

        hitBlocks.removeAll(hitBlocksToRemove);
        hitBlocksToRemove.clear();
        hitBlocks.addAll(hitBlocksToAdd);
        hitBlocksToAdd.clear();
    }


    private void spaceHit(){

        for(HitBlock currentHitBlock : hitBlocks){
            Rectangle r = new Rectangle(
                currentHitBlock.getX(),
                currentHitBlock.getY() - currentHitBlock.getHeight() / 2,
                currentHitBlock.getWidth(),
                currentHitBlock.getHeight()
            );

            if (
                r.contains(new Vector2(verticalAxis.getX(), verticalAxis.getY()))
            ){
                for (IGameAction a : currentHitBlock.getGameActions())
                    a.Execute();
            }
        }
    }

    @Override
    public void AddGreenBlock(){

        int width = GetRandomWidth(50, 20);

        HitBlock greenBlock = new HitBlock(GetRandomPointOnAxis(width)
                , width
                , Color.GREEN
                , this
        );

        greenBlock.AddGameAction(new AddPointsAction(pointsCounter, width+10));
        greenBlock.AddGameAction(new ChangeVerticalAxisAction(verticalAxis, 300));
        greenBlock.AddGameAction(new RemoveBlockAction(this, greenBlock));
        greenBlock.AddGameAction(new AddNewRandomBlock(this));
        hitBlocksToAdd.add(greenBlock);

    }

    @Override
    public void AddPurpleBlock(){
        int width = GetRandomWidth(50, 20);

        HitBlock purpleBlock = new HitBlock(GetRandomPointOnAxis(width)
                , width
                , Color.PURPLE
                , this
        );
        purpleBlock.AddGameAction(new AddPointsAction(pointsCounter, width-10));
        purpleBlock.AddGameAction(new ChangeVerticalAxisAction(verticalAxis, 300));
        purpleBlock.AddGameAction(new RemoveBlockAction(this, purpleBlock));
        purpleBlock.AddGameAction(new AddNewRandomBlock(this));
        if (hitBlocks.size()<=3){    //czemu sa 2 wiecej kwadraty? dwa sa to remove? ale to by było bez sensu 2 piewsza nie sa dodawane do listy? ale sa z niej jakos usuwane
        purpleBlock.AddGameAction(new AddNewRandomBlock(this));}

        hitBlocksToAdd.add(purpleBlock);

    }


    @Override
    public void AddRedBlock(){
        int width = GetRandomWidth(50, 20);

        HitBlock redBlock = new HitBlock(GetRandomPointOnAxis(width)
                , width
                , Color.RED
                , this
        );

        redBlock.AddGameAction(new AddPointsAction(pointsCounter, -width));
        redBlock.AddGameAction(new ChangeVerticalAxisAction(verticalAxis, 600));
        redBlock.AddGameAction(new RemoveBlockAction(this, redBlock));
        redBlock.AddGameAction(new AddNewRandomBlock(this));
        hitBlocksToAdd.add(redBlock);
    }

    private int GetRandomWidth(int max, int min) {
        Random r = new Random();
        return (int) (r.nextFloat() * (float)(max - min) + min);
    }

    private Vector2 GetRandomPointOnAxis(float offset){
        Random r = new Random();
        return new Vector2(beginOfAxis).add(new Vector2((endOfAxis.x - this.beginOfAxis.x - offset) * r.nextFloat() , 0));
    }

    public void AddVertcalAxis(){
        float width = 5;
        this.verticalAxis = new VerticalAxis(
            GetRandomPointOnAxis(width)
            , width
            , this);
    }

    public VerticalAxis ReturnVertical(){
        return verticalAxis;

    }

    @Override
    public float GetLeftBorder() {
        return beginOfAxis.x;
    }

    @Override
    public float GetRightBorder() {
        return endOfAxis.x;
    }

    Vector2 beginOfAxis;
    Vector2 endOfAxis;
    VerticalAxis verticalAxis;
    List<HitBlock> hitBlocks = new ArrayList<HitBlock>();
    List<HitBlock> hitBlocksToRemove = new ArrayList<HitBlock>();
    List<HitBlock> hitBlocksToAdd = new ArrayList<HitBlock>();
    private PointsCounter pointsCounter;

}
