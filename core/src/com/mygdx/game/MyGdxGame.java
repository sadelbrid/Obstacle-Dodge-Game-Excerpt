package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.states.GameStateManager;
import com.blinkideacompany.pop.states.SplashScreen;

import java.util.Timer;
import java.util.TimerTask;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	public ShapeRenderer s;
	Texture img;
	GameStateManager gsm;
    Timer updateTimer;

	public final static int HEIGHT=800;
	public final static int WIDTH=480;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm=new GameStateManager();
		gsm.changeState(new SplashScreen());
		s=new ShapeRenderer();
        updateTimer=new Timer();
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gsm.currentState.update();
                gsm.currentState.draw(s);
            }
        },0,20);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


	}
}
