package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletEmitter {
    private Texture bulletTexture;
    private Bullet[] bullets;
    public static final int MAX_BULLETS_COUNT = 500;

    public Bullet[] getBullets() {
        return bullets;
    }

    public BulletEmitter() {
        this.bulletTexture = new Texture("projectile.png");
        this.bullets = new Bullet[MAX_BULLETS_COUNT];
        for (int i = 0; i < bullets.length; i++) {
            this.bullets[i] = new Bullet();
        }
    }

    public void activate(float x, float y, float vx, float vy, int damage){
        for (int i = 0; i < bullets.length; i++) {
            if(!bullets[i].isActive()){
                bullets[i].activate(x, y, vx, vy, damage);
                break;
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < bullets.length; i++) {
            if(bullets[i].isActive()){
               batch.draw(bulletTexture, bullets[i].getPosition().x - 8, bullets[i].getPosition().y - 8);
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < bullets.length; i++) {
            if(bullets[i].isActive()){
                bullets[i].update(dt);
            }
        }
    }
}
