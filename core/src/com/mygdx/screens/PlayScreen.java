package com.mygdx.screens;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.conquer.Globe;
import com.mygdx.conquer.Globe.Lands;
import com.mygdx.conquer.InputController;
import com.mygdx.conquer.LandMass;

public class PlayScreen implements Screen {
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;

	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		font = new BitmapFont();
		Gdx.input.setInputProcessor(new InputController() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				final LandMass l = Globe.getLand(screenX, screenY);
				if (l == null)
					return true;
				System.out.println(l.getName());
				return super.touchDown(screenX, screenY, pointer, button);
			}
		});
		getMapClicks();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		float width = Gdx.graphics.getWidth() * 8 / 9f, height = Gdx.graphics
				.getHeight() * 4 / 5f;
		for (Lands l : Globe.Lands.values()) {
			final LandMass current = Globe.getLand(l);
			if (current == null)
				continue;
			batch.setColor(current.getCondition());
			batch.draw(current.getImage(), width / 16, 0 * 1.05f, width, height);
		}
		final int gx = Gdx.input.getX(), gy = Gdx.input.getY();
		for (final Point p : Globe.getClicks()) {
			font.draw(batch, ".", p.x, p.y);
		}

		font.draw(batch, gx + ", " + gy, gx, Gdx.graphics.getHeight() - gy);
		batch.end();
	}

	public void getMapClicks() {
		for (final LandMass l : Globe.land) {
			final TextureData td = l.getImage().getTextureData();
			td.prepare();
			final Pixmap map = td.consumePixmap();
			for (int i = 0; i < map.getWidth(); i++)
				for (int j = 0; j < map.getHeight(); j++)
					if (new Color(map.getPixel(i, j)).a == 1)
						Globe.clicked.add(new Point(i, j));

			td.disposePixmap();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
