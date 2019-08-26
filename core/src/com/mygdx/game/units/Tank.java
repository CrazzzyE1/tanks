package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.Direction;
import com.mygdx.game.utils.TankOwner;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.Weapon;

public abstract class Tank {
     GameScreen gameScreen;
     TankOwner ownerType;
     Weapon weapon;
     TextureRegion texture;
     TextureRegion textureHp;

     Vector2 tmp;
     Vector2 position;
     Circle circle;

     float speed;
     float angle;
     float turretAngle;
     float fireTimer;
     int width;
     int height;

     int hp;
     int hpMax;

    public Circle getCircle() {
        return circle;
    }

    public TankOwner getOwnerType() {
        return ownerType;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Tank(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.tmp = new Vector2(0.0f, 0.0f);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2 , width,height,1,1, angle);
        batch.draw(weapon.getTexture(), position.x - width / 2, position.y - height / 2, width / 2, height / 2 , width,height,1,1, turretAngle);
        if (hp < hpMax) {
            batch.setColor(0,0,0,0.8f);
            batch.draw(textureHp, position.x - width / 2 - 2, position.y + height / 2 - 8 + 8, 44, 12);
            batch.setColor(0,1,0,0.8f);
            batch.draw(textureHp, position.x - width / 2, position.y + height / 2 - 8 + 10, ((float)hp / hpMax) * 40, 8);
            batch.setColor(1,1,1,1);
        }


    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            destroy();
        }
    }

    public abstract void destroy ();

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

        circle.setPosition(position);

    }

    public void rotateTurretToPoint(float pointX, float pointY, float dt) {
        float angleTo = Utils.getAngle(position.x, position.y, pointX, pointY);
        turretAngle = Utils.makeRotation(turretAngle, angleTo, 270.0f, dt);
        turretAngle = Utils.angleToFromNegPiToPosPi(turretAngle);
    }

    public void move(Direction direction, float dt) {
        tmp.set(position);
        tmp.add(speed * direction.getVx() * dt, speed * direction.getVy() * dt);
        if (gameScreen.getMap().isAreaClear(tmp.x, tmp.y, width/2)){
            angle = direction.getAngel();
            position.set(tmp);
        }

    }


    public void fire() {

            if (fireTimer >= weapon.getFirePeriod()) {
                fireTimer = 0.0f;
                float angleRad = (float)Math.toRadians(turretAngle);
                gameScreen.getBulletEmitter().activate(this, position.x, position.y, weapon.getProjectileSpeed()*(float)Math.cos(angleRad), weapon.getProjectileSpeed()*(float)Math.sin(angleRad), weapon.getDamage(), weapon.getProjectileLifeTime());
            }

    }

}
