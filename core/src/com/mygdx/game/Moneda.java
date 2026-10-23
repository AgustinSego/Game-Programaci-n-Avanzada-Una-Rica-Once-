package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Moneda extends ObjetoCaida {
    private Texture textura;
    private Sound sonido;

    public Moneda(float x, float y, Texture textura, Sound sonido) {
        super(x, y, new MovimientoRecto()); // Asigna el strategy
        this.textura = textura;
    }

    @Override
    protected void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(100);
    }

    @Override
    protected Texture getTextura() { return textura; }

    @Override
    protected void reproducirSonido() {
        if (sonido != null) sonido.play();
    }
}