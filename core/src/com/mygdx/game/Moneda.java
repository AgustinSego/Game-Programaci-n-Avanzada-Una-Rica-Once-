package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Moneda extends ObjetoCaida {

    public Moneda(float x, float y, Texture textura, Sound sonido, EstrategiaMovimiento estrategia) {
        super(x, y, textura, sonido, estrategia);
    }

    @Override
    protected void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(100);
    }
}