package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoCaida {
    protected Rectangle hitbox;
    protected Texture textura;
    protected Sound sonido;
    protected EstrategiaMovimiento estrategiaMovimiento;
    protected boolean destruido = false;

    public ObjetoCaida(float x, float y, Texture textura, Sound sonido, EstrategiaMovimiento estrategia) {
        this.hitbox = new Rectangle(x, y, 64, 64);
        this.textura = textura;
        this.sonido = sonido;
        this.estrategiaMovimiento = estrategia;
    }

    // TEMPLATE METHOD: Define el esqueleto del algoritmo de actualización
    public final void actualizar(float delta) {
        Tarro tarro = Tarro.getInstance();
        if (destruido || tarro == null) return;

        // 1. Mover (delegado a Strategy)
        if (estrategiaMovimiento != null) {
            estrategiaMovimiento.mover(hitbox, tarro.getArea(), delta);
        }

        // 2. Verificar si sale de la pantalla
        if (hitbox.y + hitbox.height < 0) {
            destruido = true;
            return;
        }

        // 3. Verificar colisión con el jugador
        if (hitbox.overlaps(tarro.getArea())) {
            aplicarEfecto(tarro);
            reproducirSonido();
            destruido = true;
        }
    }

    public void dibujar(SpriteBatch batch) {
        if (!destruido && textura != null) {
            batch.draw(textura, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

    public boolean estaDestruido() {
        return destruido;
    }

    public void setEstrategiaMovimiento(EstrategiaMovimiento estrategiaMovimiento) {
        this.estrategiaMovimiento = estrategiaMovimiento;
    }

    public EstrategiaMovimiento getEstrategiaMovimiento() {
        return estrategiaMovimiento;
    }

    // Paso primitivo obligatorio para las subclases
    protected abstract void aplicarEfecto(Tarro tarro);

    // Hook opcional: las subclases pueden sobrescribirlo o heredar el comportamiento por defecto
    protected void reproducirSonido() {
        if (sonido != null) {
            sonido.play();
        }
    }
}