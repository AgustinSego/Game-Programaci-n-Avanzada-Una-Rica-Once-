package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

public class MovimientoZigZag implements EstrategiaMovimiento {
    private float velocidadY = 250f;
    private float tiempo = 0;

    @Override
    public void mover(Rectangle hitbox, float deltaTime, Tarro tarro) {
        hitbox.y -= velocidadY * deltaTime;
        tiempo += deltaTime;
        hitbox.x += MathUtils.sin(tiempo * 5) * 100 * deltaTime;
    }
}
