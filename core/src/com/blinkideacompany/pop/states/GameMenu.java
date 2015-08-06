package com.blinkideacompany.pop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;


public class GameMenu extends State{
    public boolean gameStarted=false;
    private Texture playButton, aboutButton;
    BitmapFont font;
    AssetManager asm;

    public GameMenu(GameStateManager gsm){
        super(gsm);
        asm=new AssetManager();
        playButton = new Texture("playButton.png");
        aboutButton = new Texture("aboutButton.png");

    }

    @Override
    public void draw(ShapeRenderer s) {
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        s.circle(100,100,100);
        s.end();
    }

    @Override
    protected void handleInput(){
        if(Gdx.input.isTouched()){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if(x > Gdx.graphics.getWidth()/2 - playButton.getWidth()/2 &&
                    x < Gdx.graphics.getWidth()/2 + playButton.getWidth()/2 &&
                    y > Gdx.graphics.getHeight()/2 - playButton.getHeight()/2 &&
                    y < Gdx.graphics.getHeight()/2 + playButton.getHeight()/2){
                //Switch states
                gsm.set(new InGame(gsm));
                dispose();
            }
            System.out.print("touching");
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playButton, MyGdxGame.WIDTH/2 - playButton.getWidth()/2,
                MyGdxGame.HEIGHT/2 - playButton.getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose(){
        playButton.dispose();
        aboutButton.dispose();
    }
}
