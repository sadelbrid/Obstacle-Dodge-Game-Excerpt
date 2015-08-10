package com.blinkideacompany.pop.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.Player;
import com.blinkideacompany.pop.obstacles.ObstacleManager;

public class InGame extends State {
    private boolean gameStarted;
    private ShapeRenderer shapeRenderer;
    ObstacleManager obstacleManager;
    Player player;
    float countdown;
    public InGame(GameStateManager gsm, ShapeRenderer sr) {
        super(gsm);
        shapeRenderer =sr;
        gameStarted = false;
        player = new Player(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        obstacleManager = new ObstacleManager(player, shapeRenderer, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        countdown = 4;
    }

    @Override
    public void update(float dt, ShapeRenderer s) {
        if(!gameStarted) countdown -= dt;
        gameStarted = countdown < 1.0f;
        if(gameStarted) {
            player.update();
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
            //Joystick
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.BLUE);
            shapeRenderer.circle(player.joyStick.x, player.joyStick.y, player.joyStick.radius);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.circle(player.x,player.y,player.size);
            shapeRenderer.end();



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

