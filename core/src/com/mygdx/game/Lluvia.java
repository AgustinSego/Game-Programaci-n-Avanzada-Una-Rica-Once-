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
	// Reemplazamos los arrays paralelos por un array de objetos polimórficos
	private Array<ObjetoCaida> objetosCaidos;
	private long lastDropTime;

	// Nuevas texturas
	private Texture texMoneda;
	private Texture texBillete;
	private Texture texMano;
	private Texture texChicle;

	private Sound dropSound;
	private Music rainMusic;

	public Lluvia(Texture texMoneda, Texture texBillete, Texture texMano, Texture texChicle, Sound dropSound, Music rainMusic) {
		this.texMoneda = texMoneda;
		this.texBillete = texBillete;
		this.texMano = texMano;
		this.texChicle = texChicle;
		this.dropSound = dropSound;
		this.rainMusic = rainMusic;
	}

	public void crear() {
		objetosCaidos = new Array<ObjetoCaida>();
		crearObjetoCaida();
		rainMusic.setLooping(true);
		rainMusic.play();
	}

	private void crearObjetoCaida() {
		float x = MathUtils.random(0, 800 - 64);
		float y = 480;
		int probabilidad = MathUtils.random(1, 100);

		// Distribución de spawn
		if (probabilidad <= 50) {
			objetosCaidos.add(new Moneda(x, y, texMoneda, dropSound)); // 50% Moneda
		} else if (probabilidad <= 60) {
			objetosCaidos.add(new Billete(x, y, texBillete, dropSound)); // 10% Billete (Raro)
		} else if (probabilidad <= 80) {
			objetosCaidos.add(new Mano(x, y, texMano)); // 20% Mano
		} else {
			objetosCaidos.add(new Chicle(x, y, texChicle)); // 20% Chicle
		}

		lastDropTime = TimeUtils.nanoTime();
	}

	public boolean actualizarMovimiento(Tarro tarro) {
		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) crearObjetoCaida(); // Genera objetos cada 1 seg aprox

		float delta = Gdx.graphics.getDeltaTime();

		for (int i = 0; i < objetosCaidos.size; i++) {
			ObjetoCaida obj = objetosCaidos.get(i);

			// EL TEMPLATE METHOD EN ACCIÓN: Mueve, revisa límites y colisiones internamente
			obj.actualizar(tarro, delta);

			if (obj.estaDestruido()) {
				objetosCaidos.removeIndex(i);
				i--; // Ajustar índice tras eliminar
			}
		}

		if (tarro.getVidas() <= 0) {
			return false; // Game Over
		}

		return true;
	}

	public void actualizarDibujoLluvia(SpriteBatch batch) {
		for (ObjetoCaida obj : objetosCaidos) {
			obj.dibujar(batch);
		}
	}

	public void destruir() {
		dropSound.dispose();
		rainMusic.dispose();
		texMoneda.dispose();
		texBillete.dispose();
		texMano.dispose();
		texChicle.dispose();
	}

	public void pausar() {
		rainMusic.stop();
	}

	public void continuar() {
		rainMusic.play();
	}
}