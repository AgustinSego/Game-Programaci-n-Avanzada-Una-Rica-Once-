package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Mano extends ObjetoCaida {
    private Texture textura;

    public Mano(float x, float y, Texture textura) {
        super(x, y, new MovimientoPersecucion());
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