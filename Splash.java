/**Splash class is a fade-in image to introduce the game, can be used in conjunction with loading,
 * and will set the tone of the game and the brand.
 * Familiarisation of methods, classes, interfaces through ligdx wiki
 * available at https://code.google.com/p/libgdx/wiki/TableOfContents
 * and following tutorials "LibGdx" available at http://obviam.net/index.php/table-of-contents
 * and YouTube user dermetfan's LibgGdx tutorial sequence available at
 * http://www.youtube.com/watch?v=EJwXzmUQChg&feature=c4-overview-vl&list=PLXY8okVWvwZ0JOwHiH1TntAdq-UDPnC2L
 * @author Lisa Tagliaferri
 * @version 1.0
 */

package com.me.kitchencabinet.screens;

import com.me.kitchencabinet.tween.*;

import aurelienribon.tweenengine.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Splash implements Screen{
	
	private SpriteBatch batch; //draws 2D rectangles that references a texture (region), class will batch drawing commands and optimize them for processing by the GPU
	private Sprite splash;
	private TweenManager tweenManager;

	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.begin();
		splash.draw(batch);
		batch.end();
		
		tweenManager.update(delta);
		
	}

	public void resize(int width, int height) {
		
	}

	public void show() {
		
		batch = new SpriteBatch();
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		/**overrides size of png file*/
		Texture.setEnforcePotImages(false);
		
		Texture splashTexture = new Texture("img/splash.png");
		
		/**bring splashTexture in as a sprite*/
		splash = new Sprite(splashTexture);
		
		/**set size of sprite*/
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		/**have sprite fade in at 6 seconds*/
		Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash, SpriteAccessor.ALPHA, 6).target(1).setCallback(new TweenCallback() {
			public void onEvent(int type, BaseTween<?> source) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		}).start(tweenManager);
		
		/**update tweenManager to have the lowest time*/
		tweenManager.update(Float.MIN_VALUE);
		
	}

	public void hide() {
		
		dispose();
	}

	public void pause() {
		
	}

	public void resume() {
		
	}

	public void dispose() {
		
		batch.dispose();
		splash.getTexture().dispose();
		
	}

}

