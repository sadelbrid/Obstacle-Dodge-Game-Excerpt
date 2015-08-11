package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.states.GameStateManager;
import com.blinkideacompany.pop.states.InGame;
import com.blinkideacompany.pop.states.SplashScreen;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	public ShapeRenderer s;
	private GameStateManager gsm;


	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		gsm = new GameStateManager();
		s=new ShapeRenderer();
		gsm.push(new InGame(gsm, shapeRenderer));
		Gdx.gl.glClearColor(1, 1, 1, 1);

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime(), shapeRenderer);
		gsm.render(batch);
	}
}
