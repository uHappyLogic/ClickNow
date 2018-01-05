package com.busy.minds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.busy.minds.gameobjects.*;
/**klasa ClickNowGame generująca wszystkie elemeny w oknie*/
public class ClickNowGame extends ApplicationAdapter implements IGameManager {
	SpriteBatch batch;
	Texture img;
	@Override
	public void create () {

		batch = new SpriteBatch();
		textBitmap =new BitmapFont();
		textBitmap.setColor(Color.BLACK);

		recordManager = new RecordManager();

		recordCounter = new PointsCounter(
				textBitmap
				, new Vector2(320, 400)
				, "Your record is %d"
		);

		recordCounter.SetPoints(recordManager.GetRecord());

		StartNewGame();
		ShowStartScreen();
	}

	@Override
	public void render () {

		float delta = Gdx.graphics.getDeltaTime();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		axis.draw(batch, 0);

		if (isGameRunning) {
			axis.act(delta);
			timer.TimerWork(delta);
			if (Gdx.input.isKeyJustPressed(Input.Keys.P))PauseTheGame();
		}

		batch.begin();
		textBitmap.draw(batch,"Press SPACE to hit the block or P to pause the game",220,150);
		textBitmap.draw(batch,"CLICK NOW GAME!!!",310,420);
		textBitmap.draw(batch, timer.DrawTime(),50,100);
		speedView.draw(batch,0);
		if (!isGameRunning) {
			if (gameHasEnd) {textBitmap.draw(batch, "Press M to start new game or R to resume", 250, 180);
			}else
				textBitmap.draw(batch,"Press M to start new game",300,180);


			if (Gdx.input.isKeyJustPressed(Input.Keys.M)){
				StartNewGame();
			}else
				if(Gdx.input.isKeyJustPressed(Input.Keys.R))ResumeTheGame();
		}

		pointsCounter.draw(batch, 0);
		recordCounter.draw(batch, 0);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		textBitmap.dispose();
	}
	/**rozpoczyna nową gre*/
	@Override
	public void StartNewGame() {

		timeSeconds =50;
		timeMinutes = 0;

		pointsCounter = new PointsCounter(
				textBitmap
				, new Vector2(320, 380)
				, "You have earned %d"
		);

		axis= new Axis(new Vector2(50, 275 ), new Vector2(750,275), pointsCounter);

		axis.AddVertcalAxis();
		axis.AddGreenBlock();
		axis.AddRedBlock();
		speedView=new SpeedView(textBitmap,axis.ReturnVertical());
		timer=new Timer(timeSeconds,timeMinutes, this);
		gameHasEnd=true;
		isGameRunning = true;

	}
	/**konczy gre, umozliwia rozpoczęcie nowej*/
	@Override
	public void ShowStartScreen() {

		if (pointsCounter.GetPoints() > recordManager.GetRecord()) {
			recordManager.SetRecord(pointsCounter.GetPoints());
			recordCounter.SetPoints(pointsCounter.GetPoints());
		}
		gameHasEnd=false;
		isGameRunning = false;
	}
	/**wznawia gre*/
	@Override
	public void ResumeTheGame() {
		if (gameHasEnd)
		isGameRunning = true;
	}
	/**pauzuje grę*/
	@Override
	public void PauseTheGame() {
		isGameRunning = false;
	}


	/**czas w sekundach*/
	private int timeSeconds;
	/**czas trwania gry w minuach*/
	private int timeMinutes;
	/**obiekt klasy BitmmapFont*/
	private BitmapFont textBitmap;
	/**obiekt klasy Timer*/
	private Timer timer;
	/**obiekt klasy Axis*/
	private Axis axis;
	/**obiekt klasy SpeedView*/
	private SpeedView speedView;
	/**obiekt klasy PointsCounter*/
	private PointsCounter recordCounter;
	/**obiekt klasy PointsCounter*/
	private PointsCounter pointsCounter;
	/**zmienna okreslajacz czy gra jest w toku czy nie*/
	private boolean isGameRunning;
	/**interface RecordManager*/
	private RecordManager recordManager;
	/**informuje o tym czy gra sie zakończyła*/
	private boolean gameHasEnd;
}
