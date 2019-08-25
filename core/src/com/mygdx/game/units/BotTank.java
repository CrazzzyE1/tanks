package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Weapon;
import com.mygdx.game.utils.Direction;

public class BotTank extends Tank {
    Direction preferredDirection;
    float aiTimer;
    float aiTimerTo;
    boolean active;

    public boolean isActive() {
        return active;
    }

    public BotTank(MyGdxGame game, TextureAtlas atlas) {
        super(game);
        this.weapon = new Weapon(atlas);
        this.texture = atlas.findRegion("botTankBase");
        this.position = new Vector2(500.0f, 500.0f);
        this.speed = 100.0f;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 3;
        this.hp = this.hpMax;
        this.aiTimerTo = 3.0f;
        this.preferredDirection = Direction.UP;
    }

    public void activate (float x, float y) {
        hpMax = 3;
        hp = hpMax;
        preferredDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
        angle = preferredDirection.getAngel();
        position.set(x, y);
        aiTimer = 0.0f;
        active = true;
    }

    public void update(float dt) {
        aiTimer += dt;
        fireTimer += dt;
        if(aiTimer >= aiTimerTo) {
            aiTimer = 0.0f;
            aiTimerTo = MathUtils.random(2.5f, 4.0f);
            preferredDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
            angle = preferredDirection.getAngel();
        }
        position.add(speed * preferredDirection.getVx() * dt, speed * preferredDirection.getVy() * dt);
    }
}
