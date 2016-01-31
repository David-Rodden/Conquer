package com.mygdx.conquer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.screens.PlayScreen;

public class ConquerMain extends Game {

	public static final String TITLE = "Conquer";

	@Override
	public void create() {
		setScreen(new PlayScreen());
		Gdx.app.log(TITLE, "Launched");
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
