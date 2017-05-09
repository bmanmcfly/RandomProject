package com.ninjahero.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.ninjahero.game.InitGame.TAG;

/**
 * Created by Bman on 4/29/2017.
 *  This is the splash screen that will load starter assets for the menu screen,
 *  then open the start menu screen
 *
 */

public class SplashScreen implements Screen {
    private SpriteBatch batch;
    private Sprite splashScreen;
    private Texture image;
    @Override
    public void show() {
        batch = new SpriteBatch();
        image = new Texture("badlogic.jpg");
        splashScreen = new Sprite(image);
        splashScreen.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        splashScreen.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "Resize" + Float.toString(width)+ ", "+ Float.toString(height));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();

    }
}
