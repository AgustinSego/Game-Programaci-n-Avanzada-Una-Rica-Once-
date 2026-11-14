package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	private final GameLluviaMenu game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private Lluvia lluvia;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
		this.batch = game.getBatch();
		this.font = game.getFont();

		// Inicialización única del Singleton Tarro si aún no existe en la sesión
		if (Tarro.getInstance() == null) {
			Texture bucketTex = new Texture(Gdx.files.internal("bucket.png"));
			Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
			Tarro.getInstance(bucketTex, hurtSound);
		}
		Tarro.getInstance().reiniciar();

		// Cargar recursos para los objetos de lluvia
		Texture texMoneda = new Texture(Gdx.files.internal("moneda.png"));
		Texture texBillete = new Texture(Gdx.files.internal("billete.png"));
		Texture texMano = new Texture(Gdx.files.internal("mano.png"));
		Texture texChicle = new Texture(Gdx.files.internal("chicle.png"));

		Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("corxea.mp3"));

		// Configurar Lluvia cumpliendo OCP (sin condicionales rígidos internos)
		lluvia = new Lluvia(rainMusic);
		lluvia.registrarObjeto(new ConfiguracionObjetoCaida(
				50, new MonedaBuilder(), texMoneda, dropSound,
				new ProveedorEstrategia() {
					@Override
					public EstrategiaMovimiento crear() {
						return new MovimientoRecto();
					}
				}
		));
		lluvia.registrarObjeto(new ConfiguracionObjetoCaida(
				10, new BilleteBuilder(), texBillete, dropSound,
				new ProveedorEstrategia() {
					@Override
					public EstrategiaMovimiento crear() {
						return new MovimientoZigZag();
					}
				}
		));
		lluvia.registrarObjeto(new ConfiguracionObjetoCaida(
				20, new ManoBuilder(), texMano, null,
				new ProveedorEstrategia() {
					@Override
					public EstrategiaMovimiento crear() {
						return new MovimientoPersecucion();
					}
				}
		));
		lluvia.registrarObjeto(new ConfiguracionObjetoCaida(
				20, new ChicleBuilder(), texChicle, null,
				new ProveedorEstrategia() {
					@Override
					public EstrategiaMovimiento crear() {
						return new MovimientoRecto();
					}
				}
		));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		lluvia.crear();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.08f, 0.35f, 0.18f, 1f); // COLOR VERDE PARA SIMULAR UNA PLAZA.
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Tarro tarro = Tarro.getInstance();

		batch.begin();

		font.draw(batch, "Plata: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 475);

		if (!tarro.estaHerido()) {
			tarro.actualizarMovimiento();

			if (!lluvia.actualizarMovimiento()) {
				if (game.getHigherScore() < tarro.getPuntos()) {
					game.setHigherScore(tarro.getPuntos());
				}

				game.setScreen(new GameOverScreen(game));
				dispose();
				batch.end();
				return;
			}
		}

		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() { lluvia.continuar(); }

	@Override
	public void hide() {}

	@Override
	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this));
	}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		// El Singleton Tarro persiste entre partidas; solo liberamos los recursos de la lluvia
		lluvia.destruir();
	}
}