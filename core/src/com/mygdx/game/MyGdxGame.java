package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.states.GameMenu;
import com.blinkideacompany.pop.states.GameStateManager;
import com.blinkideacompany.pop.states.SplashScreen;

import java.util.Timer;
import java.util.TimerTask;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	public ShapeRenderer s;
	private GameStateManager gsm;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 300;


	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		gsm = new GameStateManager();
		s=new ShapeRenderer();
		gsm.push(new SplashScreen(gsm));
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime(), shapeRenderer);
		gsm.render(batch);
	}
}
