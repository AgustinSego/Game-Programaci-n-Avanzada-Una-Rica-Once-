package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class MovimientoRecto implements EstrategiaMovimiento {
    private float velocidadY = 300f;

    @Override
    public void mover(Rectangle hitbox, Rectangle objetivo, float delta) {
        hitbox.y -= velocidadY * delta;
    }
}
