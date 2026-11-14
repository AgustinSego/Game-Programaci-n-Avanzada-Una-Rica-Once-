package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ManoBuilder implements BuilderObjetoCaida {
    private float x;
    private float y;
    private Texture textura;
    private EstrategiaMovimiento estrategia;

    public ManoBuilder() {
        reset();
    }

    @Override
    public void reset() {
        this.x = 0;
        this.y = 0;
        this.textura = null;
        this.estrategia = new MovimientoPersecucion();
    }

    @Override
    public BuilderObjetoCaida setPosicion(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public BuilderObjetoCaida setTextura(Texture textura) {
        this.textura = textura;
        return this;
    }

    @Override
    public BuilderObjetoCaida setSonido(Sound sonido) {
        // Este objeto no utiliza sonido de recolección
        return this;
    }

    @Override
    public BuilderObjetoCaida setEstrategiaMovimiento(EstrategiaMovimiento estrategia) {
        if (estrategia != null) {
            this.estrategia = estrategia;
        }
        return this;
    }

    @Override
    public ObjetoCaida getResult() {
        Mano producto = new Mano(x, y, textura, estrategia);
        reset();
        return producto;
    }
}