package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<ObjetoCaida> objetosCaidos;
	private Array<ConfiguracionObjetoCaida> configuraciones;
	private DirectorObjetos director;
	private Music rainMusic;
	private long lastDropTime;
	private int pesoTotal;

	public Lluvia(Music rainMusic) {
		this.rainMusic = rainMusic;
		this.configuraciones = new Array<ConfiguracionObjetoCaida>();
		this.director = new DirectorObjetos();
		this.pesoTotal = 0;
	}

	public void registrarObjeto(ConfiguracionObjetoCaida configuracion) {
		if (configuracion != null && configuracion.getPesoProbabilidad() > 0) {
			configuraciones.add(configuracion);
			pesoTotal += configuracion.getPesoProbabilidad();
		}
	}

	public void crear() {
		objetosCaidos = new Array<ObjetoCaida>();
		crearObjetoCaida();
		if (rainMusic != null) {
			rainMusic.setLooping(true);
			rainMusic.play();
		}
	}

	private void crearObjetoCaida() {
		if (configuraciones.size == 0 || pesoTotal <= 0) return;

		float x = MathUtils.random(0, 800 - 64);
		float y = 480;
		int probabilidad = MathUtils.random(1, pesoTotal);
		int acumulado = 0;

		for (ConfiguracionObjetoCaida config : configuraciones) {
			acumulado += config.getPesoProbabilidad();
			if (probabilidad <= acumulado) {
				ObjetoCaida nuevo = director.construirObjeto(
						config.getBuilder(),
						x,
						y,
						config.getTextura(),
						config.getSonido(),
						config.crearEstrategia()
				);
				objetosCaidos.add(nuevo);
				break;
			}
		}

		lastDropTime = TimeUtils.nanoTime();
	}

	public boolean actualizarMovimiento() {
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000L) {
			crearObjetoCaida();
		}

		float delta = Gdx.graphics.getDeltaTime();
		Tarro tarro = Tarro.getInstance();

		for (int i = 0; i < objetosCaidos.size; i++) {
			ObjetoCaida obj = objetosCaidos.get(i);
			obj.actualizar(delta);

			if (obj.estaDestruido()) {
				objetosCaidos.removeIndex(i);
				i--;
			}
		}

		return tarro != null && tarro.getVidas() > 0;
	}

	public void actualizarDibujoLluvia(SpriteBatch batch) {
		for (ObjetoCaida obj : objetosCaidos) {
			obj.dibujar(batch);
		}
	}

	public void destruir() {
		if (rainMusic != null) {
			rainMusic.dispose();
		}

		Array<Texture> texturasLiberadas = new Array<Texture>();
		Array<Sound> sonidosLiberados = new Array<Sound>();

		for (ConfiguracionObjetoCaida config : configuraciones) {
			Texture tex = config.getTextura();
			if (tex != null && !texturasLiberadas.contains(tex, true)) {
				tex.dispose();
				texturasLiberadas.add(tex);
			}
			Sound snd = config.getSonido();
			if (snd != null && !sonidosLiberados.contains(snd, true)) {
				snd.dispose();
				sonidosLiberados.add(snd);
			}
		}
	}

	public void pausar() {
		if (rainMusic != null) {
			rainMusic.stop();
		}
	}

	public void continuar() {
		if (rainMusic != null) {
			rainMusic.play();
		}
	}
}