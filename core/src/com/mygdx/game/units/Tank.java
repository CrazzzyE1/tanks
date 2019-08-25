package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.Weapon;

public abstract class Tank {
     MyGdxGame game;
     Weapon weapon;
     Texture texture;
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
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2 , width,height,1,1, angle, 0, 0, width,height,false,false);
        batch.draw(weapon.getTexture(), position.x - width / 2, position.y - height / 2, width / 2, height / 2 , width,height,1,1, turretAngle, 0, 0, width,height,false,false);

    }

    public abstract void update(float dt);

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
