package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ObjetoBuilder {
    private float x;
    private float y;
    private Texture textura;
    private Sound sonido;

    // Métodos de configuración (devuelven el mismo objeto para encadenarlos)
    public ObjetoBuilder setPosicion(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ObjetoBuilder setTextura(Texture textura) {
        this.textura = textura;
        return this;
    }

    public ObjetoBuilder setSonido(Sound sonido) {
        this.sonido = sonido;
        return this;
    }

    // Métodos de construcción final (Build)
    public Moneda buildMoneda() {
        return new Moneda(x, y, textura, sonido);
    }

    public Billete buildBillete() {
        return new Billete(x, y, textura, sonido);
    }

    public Mano buildMano() {
        // La mano no necesita sonido extra, no lo pasamos
        return new Mano(x, y, textura);
    }

    public Chicle buildChicle() {
        // El chicle tampoco necesita sonido extra
        return new Chicle(x, y, textura);
    }
}