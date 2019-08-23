package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public Bullet() {
        texture = new Texture("projectile.png");
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.active = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 8, position.y - 8);
    }

    public void deactivate () {
        active = false;
    }

    public void activate (float x, float y, float vx, float vy) {
        this.active = true;
        this.position.set(x, y);
        this.velocity.set(vx, vy);
    }

    public void update (float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < 0.0f || position.x > 1280.0f || position.y < 0.0f || position.y > 720.0f) {
            active = false;
        }
    }
}
