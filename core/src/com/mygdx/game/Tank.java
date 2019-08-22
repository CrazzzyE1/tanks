package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        speed = 100;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void update(float dt) {
        checkMovement(dt);
    }

    public void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= speed * dt;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += speed * dt;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += speed * dt;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= speed * dt;
            return;
        }
    }

}
