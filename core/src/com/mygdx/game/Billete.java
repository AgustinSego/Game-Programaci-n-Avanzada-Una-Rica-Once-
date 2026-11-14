package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Billete extends ObjetoCaida {

    public Billete(float x, float y, Texture textura, Sound sonido, EstrategiaMovimiento estrategia) {
        super(x, y, textura, sonido, estrategia);
    }

    @Override
    protected void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(1000);
    }
}