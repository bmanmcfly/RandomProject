package com.ninjahero.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ninjahero.game.Snake.SnakeScreen;

public class InitGame extends Game {
	public static final String TAG = "FIRST GAME VERSION 0.0.1";
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new SnakeScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void resize (int width, int height) {
//		Gdx.app.log(TAG, "super resize");
		super.resize(width, height);

	}

	@Override
	public void pause(){
		super.pause();
	}



	@Override
	public void dispose () {
//		Gdx.app.log(TAG, "Dispose ");
		super.dispose();
		batch.dispose();
	}
}
