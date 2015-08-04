package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.states.GameStateManager;
import com.blinkideacompany.pop.states.SplashScreen;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer s;
	Texture img;
	GameStateManager gsm;

	public final static int HEIGHT=800;
	public final static int WIDTH=480;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm=new GameStateManager();
		gsm.changeState(new SplashScreen());
		s=new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.currentState.draw(s);

	}
}
