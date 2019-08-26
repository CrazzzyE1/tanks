package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.units.BotTank;
import com.mygdx.game.units.PlayerTank;
import com.mygdx.game.units.Tank;

// to 4 version - Note REALISE!!!
public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font24;
	private Map map;
	private PlayerTank player ;
	private BulletEmitter bulletEmitter;
	private BotEmitter botEmitter;
	private float gameTimer;

	private static final boolean FRIENDLY_FIRE = false;

	public PlayerTank getPlayer() {
		return player;
	}

	public BulletEmitter getBulletEmitter() {
		return bulletEmitter;
	}

	public Map getMap() {
		return map;
	}

	@Override
	public void create () {
		TextureAtlas atlas = new TextureAtlas("game.pack");
		batch = new SpriteBatch();
		font24 = new BitmapFont(Gdx.files.internal("font24.fnt"));
		map = new Map(atlas);
		player = new PlayerTank(this, atlas);
		bulletEmitter = new BulletEmitter(atlas);
		botEmitter = new BotEmitter(this, atlas);
		gameTimer = 6.0f;


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
		botEmitter.render(batch);
		bulletEmitter.render(batch);
		player.renderHUD(batch, font24);

		batch.end();
	}

	public void update(float dt) {
		gameTimer += dt;
		if (gameTimer > 5.0f){
			gameTimer = 0.0f;

			float coordX, coordY;
			do {
				coordX = MathUtils.random(0, Gdx.graphics.getWidth());
				coordY = MathUtils.random(0, Gdx.graphics.getHeight());

			} while (!map.isAreaClear(coordX,coordY,20));

			botEmitter.activate(coordX, coordY);
		}
		player.update(dt);
		botEmitter.update(dt);
		bulletEmitter.update(dt);
		checkCollisions();

	}

	public void checkCollisions () {
		for (int i = 0; i < bulletEmitter.getBullets().length; i++) {
			Bullet bullet = bulletEmitter.getBullets()[i];
			if (bullet.isActive()){
				for (int j = 0; j < botEmitter.getBots().length; j++) {
					BotTank bot = botEmitter.getBots()[j];
					if (bot.isActive()) {
						if (checkBulletAndTank(bot, bullet) && bot.getCircle().contains(bullet.getPosition())) {
							bullet.deactivate();
							bot.takeDamage(bullet.getDamage());
							break;
						}
					}
				}
				if (checkBulletAndTank(player, bullet) && player.getCircle().contains(bullet.getPosition())) {
					bullet.deactivate();
					player.takeDamage(bullet.getDamage());
				}

				map.checkWallAndBulletsCollision(bullet);
			}
		}
	}

	public boolean checkBulletAndTank(Tank tank, Bullet bullet) {
		if (!FRIENDLY_FIRE) {
			return tank.getOwnerType() != bullet.getOwner().getOwnerType();
		} else {
			return  tank != bullet.getOwner();
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
