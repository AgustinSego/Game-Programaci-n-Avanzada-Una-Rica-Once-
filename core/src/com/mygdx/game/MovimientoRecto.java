package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class MovimientoRecto implements EstrategiaMovimiento {
    private float velocidad = 300f;

    @Override
    public void mover(Rectangle hitbox, float deltaTime, Tarro tarro) {
        hitbox.y -= velocidad * deltaTime;
    }
}
