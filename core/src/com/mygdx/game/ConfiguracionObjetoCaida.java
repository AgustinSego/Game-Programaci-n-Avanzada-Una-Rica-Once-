package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ConfiguracionObjetoCaida {
    private final int pesoProbabilidad;
    private final BuilderObjetoCaida builder;
    private final Texture textura;
    private final Sound sonido;
    private final ProveedorEstrategia proveedorEstrategia;

    public ConfiguracionObjetoCaida(int pesoProbabilidad, BuilderObjetoCaida builder,
                                    Texture textura, Sound sonido,
                                    ProveedorEstrategia proveedorEstrategia) {
        this.pesoProbabilidad = pesoProbabilidad;
        this.builder = builder;
        this.textura = textura;
        this.sonido = sonido;
        this.proveedorEstrategia = proveedorEstrategia;
    }

    public int getPesoProbabilidad() {
        return pesoProbabilidad;
    }

    public BuilderObjetoCaida getBuilder() {
        return builder;
    }

    public Texture getTextura() {
        return textura;
    }

    public Sound getSonido() {
        return sonido;
    }

    public EstrategiaMovimiento crearEstrategia() {
        return proveedorEstrategia.crear();
    }
}