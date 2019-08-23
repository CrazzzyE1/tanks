package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Weapon {
    private Texture texture;
    private float firePeriod;
    private int damage;


    public Texture getTexture() {
        return texture;
    }

    public int getDamage() {
        return damage;
    }

    public Weapon() {
        this.texture = new Texture("simple_weapon.png");
        this.firePeriod = 0.1f;
        this.damage = 1;
    }

    public float getFirePeriod() {
        return firePeriod;
    }
}
