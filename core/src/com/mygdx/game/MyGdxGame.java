package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.units.PlayerTank;
import com.mygdx.game.units.Tank;

// to Second version - Note REALISE!!!
public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Map map;
	private PlayerTank player ;
	private BulletEmitter bulletEmitter;

	public BulletEmitter getBulletEmitter() {
		return bulletEmitter;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		map = new Map();
		player = new PlayerTank(this);
		bulletEmitter = new BulletEmitter();

	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0, 0.6f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch);
		player.render(batch);
		bulletEmitter.render(batch);

		batch.end();
	}

	public void update(float dt) {
		player.update(dt);
		bulletEmitter.update(dt);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
