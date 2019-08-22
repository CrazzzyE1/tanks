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
    private float angle;

    public Tank() {
        texture = new Texture("Tank.png");
        x = 0;
        y = 0;
        speed = 100;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 16, y - 16, 16, 16 , 32,32,1,1, angle, 0, 0, 32,32,false,false);

    }

    public void update(float dt) {
        checkMovement(dt);
    }

    public void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= speed * dt;
            angle = 180.0f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += speed * dt;
            angle = 0.0f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += speed * dt;
            angle = 90.0f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= speed * dt;
            angle = 270.0f;
            return;
        }
    }

}
