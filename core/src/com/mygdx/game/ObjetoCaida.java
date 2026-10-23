package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoCaida {
    protected Rectangle hitbox;
    protected EstrategiaMovimiento estrategiaMovimiento;
    protected boolean destruido = false;

    public ObjetoCaida(float x, float y, EstrategiaMovimiento estrategia) {
        hitbox = new Rectangle(x, y, 64, 64);
        this.estrategiaMovimiento = estrategia;
    }

    // TEMPLATE METHOD: Define el esqueleto del comportamiento
    public final void actualizar(Tarro tarro, float delta) {
        if (destruido) return;

        // 1. Mover (usando Strategy)
        estrategiaMovimiento.mover(hitbox, tarro.getArea(), delta);

        // 2. Verificar si sale de la pantalla
        if (hitbox.y + hitbox.height < 0) {
            destruido = true;
        }

        // 3. Verificar colisión con el jugador
        if (hitbox.overlaps(tarro.getArea())) {
            aplicarEfecto(tarro);
            reproducirSonido();
            destruido = true;
        }
    }

    public void dibujar(SpriteBatch batch) {
        if (!destruido) {
            batch.draw(getTextura(), hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

    public boolean estaDestruido() {
        return destruido;
    }

    // Pasos que las clases hijas DEBEN implementar
    protected abstract void aplicarEfecto(Tarro tarro);
    protected abstract Texture getTextura();
    protected abstract void reproducirSonido();
}
