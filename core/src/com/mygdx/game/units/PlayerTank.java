package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Weapon;
import com.mygdx.game.utils.Direction;
import com.mygdx.game.utils.TankOwner;

public class PlayerTank extends Tank {
    int scores;
    int lives;


    public PlayerTank(MyGdxGame game, TextureAtlas atlas) {
        super(game);
        this.ownerType = TankOwner.PLAYER;
        this.weapon = new Weapon(atlas);
        this.texture = atlas.findRegion("playerTankBase");
        this.textureHp = atlas.findRegion("bar");
        this.position = new Vector2(100.0f, 100.0f);
        this.speed = 100.0f;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 10;
        this.hp = this.hpMax;
        this.circle = new Circle(position.x, position.y, (width + height)/2);
        this.lives = 5;
    }

    public void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            move(Direction.LEFT, dt);
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            move(Direction.RIGHT, dt);
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            move(Direction.UP, dt);
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            move(Direction.DOWN, dt);
            return;
        }
    }

    public void getScore(int amount) {
        scores += amount;
    }

    @Override
    public void destroy() {
        lives--;
        hp = hpMax;
    }

    public void renderHUD(SpriteBatch batch, BitmapFont font24) {
        font24.draw(batch, "Score: " + scores + "\nLives: " + lives, 10, 700);
    }

    public void update(float dt) {
        checkMovement(dt);
        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();

        rotateTurretToPoint(mx, my, dt);

        if (Gdx.input.isTouched()){
            fire();
        }
        super.update(dt);
    }
}
