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
/**klasa osi poziomej*/
public class Axis extends Actor implements IBordersProvider, IBlockManager {

    public Axis(Vector2 begin, Vector2 end, PointsCounter pointsCounter){
        this.pointsCounter = pointsCounter;
        this.beginOfAxis = begin;
        this.endOfAxis = end;
    }
    /**dodanie do lsty hitBlocksToRemove hitBlocka*/
    @Override
    public void RemoveBlock(HitBlock hitBlock){
        hitBlocksToRemove.add(hitBlock);
    }
    /**metoda tworząca zarys osi poziomej*/
    @Override
    public void draw(Batch batch, float parentAlpha) {

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        //ustawienie wypełnieniaosi
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //stawienie koloru osi pozioej
        shapeRenderer.setColor(0, 0, 0, 1);
        //nadanie kształtu osi poziomej
        shapeRenderer.rectLine(beginOfAxis, endOfAxis, 5);
        shapeRenderer.end();

        //ryzowanie bloków na osi
        for (HitBlock currentHitBlock : hitBlocks) {
            currentHitBlock.draw(batch,parentAlpha);
        }

        if (verticalAxis != null)
            verticalAxis.draw(batch,parentAlpha);
    }
/**metoda pozwalająca na poruszanie się osi pionowej*/
    @Override
    public void act(float delta) {
        super.act(delta);

        if (verticalAxis != null)
            verticalAxis.act(delta);

        //przemieszczanie sie bloków po osi
        for (HitBlock hitBlock : hitBlocks)
            hitBlock.act(delta);

        //akcja wywoływana przez naciśnięcie spacji
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            spaceHit();
        }
        //czyszczenie listy hitblocks z elementów zawartych w hitBlocksToRemove
        hitBlocks.removeAll(hitBlocksToRemove);
        //cyszczenie listy
        hitBlocksToRemove.clear();
        //dodawanie do listy
        hitBlocks.addAll(hitBlocksToAdd);
        hitBlocksToAdd.clear();
    }

    /**metoda sprawdzająca czy gracz trafił w hitBlocka i wyonuje akcje bloku który został trafiony*/
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
                //wykonywane sas wwszystkie akcje gry w zaleznosci jaki trfimy hitBlock
                for (IGameAction a : currentHitBlock.getGameActions())
                    a.Execute();
            }
        }
    }
/**metoda pozwalająca na dodanie zielonego boku*/
    @Override
    public void AddGreenBlock(){

        int width = GetRandomWidth(50, 20);

        HitBlock greenBlock = new HitBlock(GetRandomPointOnAxis(width)
                , width
                , Color.GREEN
                , this
        );
        //akcje wykonywanepodczac trafienia spacja w blok
        //dodanie punktów
        greenBlock.AddGameAction(new AddPointsAction(pointsCounter, 100-width));
        //zwiekszenie pretkosci osi pionowej
        greenBlock.AddGameAction(new ChangeVerticalAxisAction(verticalAxis, 10));
        //usuniecie tego bloku z osi poziomej
        greenBlock.AddGameAction(new RemoveBlockAction(this, greenBlock));
        //dodanie losowego bloku na oś poziomą
        greenBlock.AddGameAction(new AddNewRandomBlock(this));
        //dodanie nowego bloku do listy
        hitBlocksToAdd.add(greenBlock);

    }
    /**metoda pozwalająca na dodanie fioletwego boku*/
    @Override
    public void AddPurpleBlock(){
        int width = GetRandomWidth(50, 20);

        HitBlock purpleBlock = new HitBlock(GetRandomPointOnAxis(width)
                , width
                , Color.PURPLE
                , this
        );
        purpleBlock.AddGameAction(new AddPointsAction(pointsCounter, 50-width));
        purpleBlock.AddGameAction(new ChangeVerticalAxisAction(verticalAxis, 5));
        purpleBlock.AddGameAction(new RemoveBlockAction(this, purpleBlock));
        purpleBlock.AddGameAction(new AddNewRandomBlock(this));
        if ((hitBlocks.size()+hitBlocksToRemove.size()+hitBlocksToAdd.size())<=3){    //czemu sa 2 wiecej kwadraty? dwa sa to remove? ale to by było bez sensu 2 piewsza nie sa dodawane do listy? ale sa z niej jakos usuwane
        purpleBlock.AddGameAction(new AddNewRandomBlock(this));}

        hitBlocksToAdd.add(purpleBlock);

    }

    /**metoda pozwalająca na dodanie czerwonego boku*/
    @Override
    public void AddRedBlock(){
        int width = GetRandomWidth(50, 20);

        HitBlock redBlock = new HitBlock(GetRandomPointOnAxis(width)
                , width
                , Color.RED
                , this
        );

        redBlock.AddGameAction(new AddPointsAction(pointsCounter, -width));
        redBlock.AddGameAction(new ChangeVerticalAxisAction(verticalAxis, 30));
        redBlock.AddGameAction(new RemoveBlockAction(this, redBlock));
        redBlock.AddGameAction(new AddNewRandomBlock(this));
        hitBlocksToAdd.add(redBlock);
    }
/**metoda zwracająca losową artosc z zakresu max, min*/
    private int GetRandomWidth(int max, int min) {
        Random r = new Random();
        return (int) (r.nextFloat() * (float)(max - min) + min);
    }
    /**metoda zwracająca losowy punkt na osi*/
    private Vector2 GetRandomPointOnAxis(float offset){
        Random r = new Random();
        return new Vector2(beginOfAxis).add(new Vector2((endOfAxis.x - this.beginOfAxis.x - offset) * r.nextFloat() , 0));//ta llinia
    }
    /**meoda tworząca obiekt klasy VerticalAxis o szerokości 5*/
    public void AddVertcalAxis(){
        float width = 5;
        this.verticalAxis = new VerticalAxis(
            GetRandomPointOnAxis(width)
            , width
            , this);
    }
    /**metoda zwracajaca os pionową*/
    public VerticalAxis ReturnVertical(){
        return verticalAxis;

    }
    /**ustawianie lerej granicy osi*/
    @Override
    public float GetLeftBorder() {
        return beginOfAxis.x;
    }
    /**ustawianie prawej granicy osi*/
    @Override
    public float GetRightBorder() {
        return endOfAxis.x;
    }
    /**początek poziomej osi*/
    Vector2 beginOfAxis;
    /**koniec poziomej osi*/
    Vector2 endOfAxis;
    /**obirkt osi pionowej*/
    VerticalAxis verticalAxis;
    /**lista bloków*/
    List<HitBlock> hitBlocks = new ArrayList<HitBlock>();
    /**lista bloków do usunięcia*/
    List<HitBlock> hitBlocksToRemove = new ArrayList<HitBlock>();
    /**lista bloków do dodania*/
    List<HitBlock> hitBlocksToAdd = new ArrayList<HitBlock>();
    /**obiekt klasy  PointCounter*/
    private PointsCounter pointsCounter;

}
