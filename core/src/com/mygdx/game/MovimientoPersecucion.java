package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class MovimientoPersecucion implements EstrategiaMovimiento {
    private float velocidadY = 200f;
    private float velocidadX = 100f;

    @Override
    public void mover(Rectangle hitbox, float deltaTime, Tarro tarro) {
        hitbox.y -= velocidadY * deltaTime;

        // Persigue la posición X del tarro
        if (hitbox.x < tarro.getArea().x) {
            hitbox.x += velocidadX * deltaTime;
        } else if (hitbox.x > tarro.getArea().x) {
            hitbox.x -= velocidadX * deltaTime;
        }
    }
}
