package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class MovimientoPersecucion implements EstrategiaMovimiento {
    private float velocidadY = 200f;
    private float velocidadX = 150f;

    @Override
    public void mover(Rectangle hitbox, Rectangle objetivo, float delta) {
        hitbox.y -= velocidadY * delta;

        // Persigue el eje X del objetivo (Tarro/Sombrero)
        if (objetivo != null) {
            if (hitbox.x < objetivo.x) {
                hitbox.x += velocidadX * delta;
            } else if (hitbox.x > objetivo.x) {
                hitbox.x -= velocidadX * delta;
            }
        }
    }
}
