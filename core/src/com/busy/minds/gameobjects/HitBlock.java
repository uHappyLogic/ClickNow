package com.busy.minds.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**lasa bloków pojaiwających ie na osi poziomej*/
public class HitBlock extends Actor {

    public HitBlock(
            Vector2 position
            , float width
            , Color color
            , IBordersProvider bordersProvider
    ){
        this.bordersProvider = bordersProvider;
        /**ustawienie watości X*/
        setX(position.x);
        /**ustawienie watości Y*/
        setY(position.y);
        /**ustawienie szerokosci HitBloka*/
        setWidth(width);
        /**ustawienie wysokości HitBloka*/
        setHeight(50);

        this.color = color;
        this.gameActions = new ArrayList<>();
        /**zmienna losowa*/
        Random random=new Random();
        RandomBlockMove(random);
        RandomSpeedOfBlock(random);

    }
    /**metoda tworząca zarys bloku*/
    @Override
    public void draw(Batch batch, float parentAlpha) {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(getX(), getY() - getHeight() / 2, getWidth(), getHeight());
        shapeRenderer.end();
        shapeRenderer.dispose();
    }
    /**ustawienie losowej prędkości bloku*/
    public void RandomSpeedOfBlock(Random random){
        if (random.nextFloat()<=0.3f){
            speed = random.nextFloat()*100+10;}
        else speed = random.nextFloat()*100;
    }
    /**ustalenie kierunku poruszania się bloków po osi poziomej*/
    public void RandomBlockMove(Random random){
        if(random.nextFloat()<=0.5f){
            jeden = -1;
        }else jeden = 1;

    }
/**nadpisanie metody, ktora mozliwia przemieszczanie się bloku z predkoscia zalezna od speed,w kierunku który określa zmienna jeden
 * oraz odbicie srodka bloku od konca osi*/
    @Override
    public void act(float delta) {
        super.act(delta);
        setX((getX()+speed * delta * jeden));
        //odbci się bloku od prawej krawiedzi osi poziomej
        if(getX() + getWidth() / 2 >= bordersProvider.GetRightBorder()){
            jeden=-1;
        }
        else {
            //odbci się bloku od lewej krawiedzi osi poziomej
            if(getX() + getWidth() / 2 <= bordersProvider.GetLeftBorder()){
                jeden=1;
            }
        }
    }

    public Color getColor(){
        return this.color;
    }

    /**metoda, która dodaje Igame IGameAction do listy gameActions*/
    public void AddGameAction(IGameAction gameAction) {
        gameActions.add(gameAction);
    }
    /**metoda, która zwraca liste gameActions*/
    public List<IGameAction> getGameActions() {
        return gameActions;
    }
    /**lista zawiaerajaca IGameAction*/
    private List<IGameAction> gameActions;
    /**prędkość HitBlocka*/
    private float speed;
    /**kierunek poruszania się HitBlocka*/
    private int jeden;
    /**kolor HitBlocka*/
    private Color color;
    /**interfejs IBorderProvider*/
    private IBordersProvider bordersProvider;

}
