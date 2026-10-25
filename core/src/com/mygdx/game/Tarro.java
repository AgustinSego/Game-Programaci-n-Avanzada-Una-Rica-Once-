package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Tarro { // SE LLAMA TARRO PERO SABEMOS QUE ES UN SOMBRERO.

	private static Tarro instancia;

	private Rectangle bucket;
	private Texture bucketImage;
	private Sound sonidoHerido;
	private int vidas = 3;
	private int puntos = 0;
	private int velx = 400;
	private boolean herido = false;
	private int tiempoHeridoMax = 50;
	private int tiempoHerido;

	// TAMAÑO DE LA IMAGEN
	private final float ANCHO_DIBUJO = 120f;
	private final float ALTO_DIBUJO = 80f;
	private float xVisual;
	private float yVisual = 20f;

	// TAMAÑO PARA LA HITBOX
	private final float ANCHO_HITBOX = 66f;
	private final float ALTO_HITBOX = 24f;

	private Tarro(Texture tex, Sound ss) {
		bucketImage = tex;
		sonidoHerido = ss;
		reiniciar();
	}

	public static Tarro getInstance(Texture textura, Sound sonido) { // CUANDO NO HAY TARRO USAR ESTE.
		if (instancia == null) instancia = new Tarro(textura, sonido); // VERIFICA SI EXISTE YA UNA INSTANCIA
		return instancia;
	}

	public static Tarro getInstance() { return instancia; } // DEVUELVE LA INSTANCIA DIRECTAMENTE.

	public int getVidas() { return vidas; }
	public int getPuntos() { return puntos; }
	public Rectangle getArea() { return bucket; }
	public void sumarPuntos(int pp) { puntos += pp; }

	public void reiniciar() {
		this.vidas = 3;
		this.puntos = 0;
		this.herido = false;
		this.tiempoHerido = 0;
		this.xVisual = 800 / 2f - ANCHO_DIBUJO / 2f;
		this.yVisual = 20f;

		this.bucket = new Rectangle();
		this.bucket.width = ANCHO_HITBOX;
		this.bucket.height = ALTO_HITBOX;

		this.bucket.x = xVisual + (ANCHO_DIBUJO - ANCHO_HITBOX) / 2f;
		this.bucket.y = yVisual + 24f;
	}

	public void dañar() {
		vidas--;
		herido = true;
		tiempoHerido = tiempoHeridoMax;
		if (sonidoHerido != null) sonidoHerido.play();
	}

	public void dibujar(SpriteBatch batch) {
		if (!herido) {
			batch.draw(bucketImage, xVisual, yVisual, ANCHO_DIBUJO, ALTO_DIBUJO);
		} else {
			batch.draw(bucketImage, xVisual, yVisual + MathUtils.random(-5, 5), ANCHO_DIBUJO, ALTO_DIBUJO);
			tiempoHerido--;
			if (tiempoHerido <= 0) herido = false;
		}
	}

	public void actualizarMovimiento() {
		float delta = Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) xVisual -= velx * delta;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) xVisual += velx * delta;

		if (xVisual < 0) xVisual = 0;
		if (xVisual > 800 - ANCHO_DIBUJO) xVisual = 800 - ANCHO_DIBUJO;

		this.bucket.x = xVisual + (ANCHO_DIBUJO - ANCHO_HITBOX) / 2f;
		this.bucket.y = yVisual + 24f;
	}

	public void destruir() {
		if (bucketImage != null) bucketImage.dispose();
		if (sonidoHerido != null) sonidoHerido.dispose();
		instancia = null;
	}

	public boolean estaHerido() { return herido; }
}