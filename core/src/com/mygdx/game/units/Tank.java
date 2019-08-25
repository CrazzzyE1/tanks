package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.Weapon;

public abstract class Tank {
     MyGdxGame game;
     Weapon weapon;
     TextureRegion texture;
     TextureRegion textureHp;

     Vector2 position;

     float speed;
     float angle;
     float turretAngle;
     float fireTimer;
     int width;
     int height;

     int hp;
     int hpMax;

    public Tank(MyGdxGame game) {
        this.game = game;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2 , width,height,1,1, angle);
        batch.draw(weapon.getTexture(), position.x - width / 2, position.y - height / 2, width / 2, height / 2 , width,height,1,1, turretAngle);
        batch.draw(textureHp, position.x - width / 2, position.y + height / 2 - 8);
    }

    public void update(float dt) {
        fireTimer += dt;
        if (position.x < 0.0f) {
            position.x = 0.0f;
        }

        if (position.x > Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth();
        }
        if (position.y < 0.0f) {
            position.y = 0.0f;
        }

        if (position.y > Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight();
        }
    }

    public void rotateTurretToPoint(float pointX, float pointY, float dt) {
        float angleTo = Utils.getAngle(position.x, position.y, pointX, pointY);
        turretAngle = Utils.makeRotation(turretAngle, angleTo, 270.0f, dt);
        turretAngle = Utils.angleToFromNegPiToPosPi(turretAngle);
    }


    public void fire(float dt) {

            if (fireTimer >= weapon.getFirePeriod()) {
                fireTimer = 0.0f;
                float angleRad = (float)Math.toRadians(turretAngle);
                game.getBulletEmitter().activate(position.x, position.y, 320.0f*(float)Math.cos(angleRad), 320.0f*(float)Math.sin(angleRad), weapon.getDamage());
            }

    }

}
