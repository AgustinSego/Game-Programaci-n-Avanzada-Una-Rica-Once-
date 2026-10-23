package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public interface EstrategiaMovimiento {
    void mover(Rectangle hitbox, float deltaTime, Tarro tarro);
}
