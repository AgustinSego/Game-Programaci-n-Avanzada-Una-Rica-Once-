package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Chicle extends ObjetoCaida {
    private Texture textura;

    public Chicle(float x, float y, Texture textura) {
        super(x, y, new MovimientoRecto());
        this.textura = textura;
    }

    @Override
    protected void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
    }

    @Override
    protected Texture getTextura() { return textura; }

    @Override
    protected void reproducirSonido() {}
}