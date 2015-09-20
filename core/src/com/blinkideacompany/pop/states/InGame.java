package com.blinkideacompany.pop.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.Player;
import com.blinkideacompany.pop.obstacles.ObstacleManager;
import com.mygdx.game.MyGdxGame;

public class InGame extends State {
    private boolean gameStarted;
    private ShapeRenderer shapeRenderer;
    ObstacleManager obstacleManager;
    Player player;
    float countdown;
    public InGame(GameStateManager gsm, ShapeRenderer sr) {
        super(gsm);
        shapeRenderer = sr;
        gameStarted = false;
        player = new Player(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        obstacleManager = new ObstacleManager(player, shapeRenderer, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        countdown = 0f;
    }

    @Override
    public void update(float dt, ShapeRenderer s) {
        if(!gameStarted) countdown -= dt;
        gameStarted = countdown < 1.0f;
        if(gameStarted) {
            player.update(dt);
            obstacleManager.update(dt);
        }
    }

    @Override
    public void handleInput(ShapeRenderer s){

    }

    @Override
    public void render(SpriteBatch sb){
        if(gameStarted){
            obstacleManager.draw(shapeRenderer);
            player.draw(shapeRenderer);
            player.joyStick.draw(shapeRenderer);
        }
    }

    @Override
    public void dispose(){

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

