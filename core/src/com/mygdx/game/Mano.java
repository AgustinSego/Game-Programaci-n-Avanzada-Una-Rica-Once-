package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Mano extends ObjetoCaida {

    public Mano(float x, float y, Texture textura, EstrategiaMovimiento estrategia) {
        super(x, y, textura, null, estrategia);
    }

    @Override
    protected void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
    }
}