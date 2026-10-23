package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class MovimientoZigZag implements EstrategiaMovimiento {
    private float velocidadY = 160f;
    private float velocidadX = 280f;
    private int direccionX = 1; // PARA QUE LADO DE LA PANTALLA SE DESPLAZA

    public MovimientoZigZag() {
        this.direccionX = Math.random() < 0.5 ? 1 : -1;
    }

    @Override
    public void mover(Rectangle hitbox, Rectangle objetivo, float delta) {

        hitbox.y -= velocidadY * delta;

        hitbox.x += direccionX * velocidadX * delta;

        // SI COLISIONA

        if (hitbox.x <= 0) {
            hitbox.x = 0;
            direccionX = 1; // CAMBIA A DERECHA
        }

        if (hitbox.x >= 800 - hitbox.width) {
            hitbox.x = 800 - hitbox.width;
            direccionX = -1; // CAMBIA A IZQUIERDA
        }
    }
}