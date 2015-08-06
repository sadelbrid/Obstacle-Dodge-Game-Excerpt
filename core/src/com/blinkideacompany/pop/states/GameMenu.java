package com.blinkideacompany.pop.states;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;


public class GameMenu extends State {
    public boolean gameStarted = false;
    private Texture playButton, aboutButton;
    BitmapFont font;
    AssetManager asm;

    public GameMenu(GameStateManager gsm) {
        super(gsm);
        asm = new AssetManager();
        playButton = new Texture("playButton.png");
        aboutButton = new Texture("aboutButton.png");

    }

    @Override
    public void draw(ShapeRenderer s) {
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        s.circle(100, 100, 100);
        s.end();
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playButton, MyGdxGame.WIDTH / 2 - playButton.getWidth() / 2,
                MyGdxGame.HEIGHT / 2 - playButton.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
        aboutButton.dispose();
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

//        int x = screenX;
//        int y = screenY;
//        if (x > Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2 &&
//                x < Gdx.graphics.getWidth() / 2 + playButton.getWidth() / 2 &&
//                y > Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2 &&
//                y < Gdx.graphics.getHeight() / 2 + playButton.getHeight() / 2) {
//            //Switch states
//            gsm.set(new InGame(gsm));
//            dispose();
//        }

        gsm.set(new InGame(gsm));
        dispose();

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
