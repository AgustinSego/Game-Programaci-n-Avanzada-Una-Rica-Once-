package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class DirectorObjetos {

    public ObjetoCaida construirObjeto(BuilderObjetoCaida builder, float x, float y,
                                       Texture textura, Sound sonido,
                                       EstrategiaMovimiento estrategia) {
        builder.reset();
        builder.setPosicion(x, y);
        builder.setTextura(textura);
        builder.setSonido(sonido);
        builder.setEstrategiaMovimiento(estrategia);
        return builder.getResult();
    }
}