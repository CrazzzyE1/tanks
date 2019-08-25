package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map {
    public static final int SAZE_X = 32;
    public static final int SAZE_Y = 18;
    public static final int CELL_SIZE = 40;


    private TextureRegion grassTexture;

    public Map(TextureAtlas atlas) {
        this.grassTexture = atlas.findRegion("grass40");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < SAZE_X; i++) {
            for (int j = 0; j < SAZE_Y; j++) {
                batch.draw(grassTexture, i * CELL_SIZE, j * CELL_SIZE);
            }
        }
    }
}
