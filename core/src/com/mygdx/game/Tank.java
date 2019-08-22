package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tank {
    private Texture texture;
    private  float x;
    private float y;
    private float speed;

    public Tank() {
        texture = new Texture("Tank.png");
        x = 100;
        y = 100;
        speed = 5;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void update() {
        x += speed;
    }

}
