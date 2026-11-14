package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BilleteBuilder implements BuilderObjetoCaida {
    private float x;
    private float y;
    private Texture textura;
    private Sound sonido;
    private EstrategiaMovimiento estrategia;

    public BilleteBuilder() {
        reset();
    }

    @Override
    public void reset() {
        this.x = 0;
        this.y = 0;
        this.textura = null;
        this.sonido = null;
        this.estrategia = new MovimientoZigZag();
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
        this.sonido = sonido;
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
        Billete producto = new Billete(x, y, textura, sonido, estrategia);
        reset();
        return producto;
    }
}