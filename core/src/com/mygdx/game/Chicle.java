package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Chicle extends ObjetoCaida {

    public Chicle(float x, float y, Texture textura, EstrategiaMovimiento estrategia) {
        super(x, y, textura, null, estrategia);
    }

    @Override
    protected void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
    }
}