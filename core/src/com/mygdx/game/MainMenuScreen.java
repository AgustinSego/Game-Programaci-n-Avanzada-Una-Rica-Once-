package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;

public class MainMenuScreen implements Screen {

	private final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;

	public MainMenuScreen(final GameLluviaMenu game) {
		this.game = game;
		this.batch = game.getBatch();
		this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.getData().setScale(2, 2);
		font.draw(batch, "Bienvenido a Una Rica Once!!! ", 100, camera.viewportHeight/2+50);
		font.draw(batch, "Pulsa ESPACIO para comenzar!", 100, camera.viewportHeight/2-50);

		batch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}
}