package com.busy.minds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.busy.minds.gameobjects.*;

public class ClickNowGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		textBitmap =new BitmapFont();
		textBitmap.setColor(Color.BLACK);
		pointsCounter = new PointsCounter(textBitmap);

		axis= new Axis(new Vector2(50, 275 ), new Vector2(750,275), pointsCounter);

		axis.AddVertcalAxis();
		axis.AddGreenBlock();
		axis.AddRedBlock();
		speedView=new SpeedView(textBitmap,axis.ReturnVertical());



		timer=new Timer(timeSeconds,timeMinutes=4);

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		axis.draw(batch, 0);
		axis.act(0.001f);
		//
		timer.TimerWork();
		batch.begin();
		//batch.draw(img, 0, 0);
		textBitmap.draw(batch,"CLICK NOW GAME!!!",320,420);//tworzenie napisów w oknie
		textBitmap.draw(batch, timer.DrawTime(),100,120);
		speedView.draw(batch,0);
		pointsCounter.draw(batch, 0);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		textBitmap.dispose();
	}
	private double timeSeconds;
	private int timeMinutes;

	private BitmapFont textBitmap;
	private Timer timer;
	private Axis axis;
	private SpeedView speedView;
	private PointsCounter pointsCounter;
}
