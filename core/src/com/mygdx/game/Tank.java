package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tank {
    private MyGdxGame game;
    private Weapon weapon;
    private Texture texture;
    private Vector2 position;

    private float speed;
    private float angle;

    private float turretAngle;
    private float fireTimer;

    private int width;
    private int height;

    public Tank(MyGdxGame game) {
        this.game = game;
        this.weapon = new Weapon();
        this.texture = new Texture("player_tank_base.png");
        this.position = new Vector2(100.0f, 100.0f);
        this.speed = 100.0f;
        this.width = texture.getWidth();
        this.height = texture.getHeight();

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2 , width,height,1,1, angle, 0, 0, width,height,false,false);
        batch.draw(weapon.getTexture(), position.x - width / 2, position.y - height / 2, width / 2, height / 2 , width,height,1,1, turretAngle, 0, 0, width,height,false,false);

    }

    public void update(float dt) {
        checkMovement(dt);
        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();
        float angleTo = Utils.getAngle(position.x, position.y, mx, my);
        turretAngle = Utils.makeRotation(turretAngle, angleTo, 270.0f, dt);
        turretAngle = Utils.angleToFromNegPiToPosPi(turretAngle);

        if (Gdx.input.isTouched()){
            fire(dt);
        }
    }

    public void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.x -= speed * dt;
            angle = 180.0f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            position.x += speed * dt;
            angle = 0.0f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            position.y += speed * dt;
            angle = 90.0f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.y -= speed * dt;
            angle = 270.0f;
            return;
        }
    }

    public void fire(float dt) {
            fireTimer += dt;
            if (fireTimer >= weapon.getFirePeriod()) {
                fireTimer = 0.0f;
                float angleRad = (float)Math.toRadians(turretAngle);
                game.getBulletEmitter().activate(position.x, position.y, 320.0f*(float)Math.cos(angleRad), 320.0f*(float)Math.sin(angleRad));
            }

    }

}
