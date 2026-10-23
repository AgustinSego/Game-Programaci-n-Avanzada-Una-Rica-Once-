package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Billete extends ObjetoCaida {
    private Texture textura;
    private Sound sonido;

    public Billete(float x, float y, Texture textura, Sound sonido) {
        super(x, y, new MovimientoZigZag());
        this.textura = textura;
    }

    @Override
    protected void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(1000);
    }

    @Override
    protected Texture getTextura() { return textura; }

    @Override
    protected void reproducirSonido() {
        if (sonido != null) sonido.play();
    }
}
