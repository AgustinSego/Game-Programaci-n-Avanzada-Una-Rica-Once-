package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface BuilderObjetoCaida {
    void reset();
    BuilderObjetoCaida setPosicion(float x, float y);
    BuilderObjetoCaida setTextura(Texture textura);
    BuilderObjetoCaida setSonido(Sound sonido);
    BuilderObjetoCaida setEstrategiaMovimiento(EstrategiaMovimiento estrategia);
    ObjetoCaida getResult();
}