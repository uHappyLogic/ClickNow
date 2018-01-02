package com.busy.minds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.busy.minds.gameobjects.*;

public class ClickNowGame extends ApplicationAdapter implements IGameManager {
	SpriteBatch batch;
	Texture img;
	@Override
	public void create () {
		batch = new SpriteBatch();
		textBitmap =new BitmapFont();
		textBitmap.setColor(Color.BLACK);

		StartNewGame();
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
		}

		batch.begin();

		if (!isGameRunning) {
			textBitmap.draw(batch,"Press spacebar to start",320,420);

			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				StartNewGame();
			}
		} else {
			textBitmap.draw(batch,"CLICK NOW GAME!!!",320,420);//tworzenie napisów w oknie
			textBitmap.draw(batch, timer.DrawTime(),100,120);
			speedView.draw(batch,0);
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

	@Override
	public void StartNewGame() {

		timeSeconds = 15;
		timeMinutes = 0;

		pointsCounter = new PointsCounter(
				textBitmap
				, new Vector2(320, 380)
				, "You have earned %d"
		);

		recordCounter = new PointsCounter(
				textBitmap
				, new Vector2(320, 400)
				, "Your record is %d"
		);

		axis= new Axis(new Vector2(50, 275 ), new Vector2(750,275), pointsCounter);

		axis.AddVertcalAxis();
		axis.AddGreenBlock();
		axis.AddRedBlock();
		speedView=new SpeedView(textBitmap,axis.ReturnVertical());
		timer=new Timer(timeSeconds,timeMinutes, this);

		isGameRunning = true;
	}

	@Override
	public void ShowStartScreen() {


		isGameRunning = false;
	}

	private int timeSeconds;
	private int timeMinutes;

	private BitmapFont textBitmap;
	private Timer timer;
	private Axis axis;
	private SpeedView speedView;
	private PointsCounter recordCounter;
	private PointsCounter pointsCounter;
	private boolean isGameRunning;
}
