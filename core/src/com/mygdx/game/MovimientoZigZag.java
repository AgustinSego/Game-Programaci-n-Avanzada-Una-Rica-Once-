package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class MovimientoZigZag implements EstrategiaMovimiento {
    private float velocidadY = 250f;
    private float amplitudX = 150f;
    private float posXInicial;
    private boolean inicioRegistrado = false;

    @Override
    public void mover(Rectangle hitbox, Rectangle objetivo, float delta) {
        if (!inicioRegistrado) {
            posXInicial = hitbox.x;
            inicioRegistrado = true;
        }

        hitbox.y -= velocidadY * delta;
        // Movimiento senoidal para el ZigZag
        hitbox.x = posXInicial + MathUtils.sin(TimeUtils.millis() / 300f) * amplitudX;
    }
}
