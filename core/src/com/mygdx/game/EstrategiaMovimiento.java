package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public interface EstrategiaMovimiento {
    void mover(Rectangle hitbox, Rectangle objetivo, float delta);
}
