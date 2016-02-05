/**Chips class to start preparation of chips. Will sequence across screens, first to cut the potato,
 * then to fry the potato, then to reveal completed chips, then for a rating based on a Goldilocks scale,
 * and nutritional feedback, eventually the user will continue to the next food item to cook or be returned
 * to the city menu.
 * Developed using libGDX library
 * Familiarisation of methods, classes, interfaces through ligdx wiki
 * available at https://code.google.com/p/libgdx/wiki/TableOfContents
 * and following tutorials "LibGdx" available at http://obviam.net/index.php/table-of-contents
 * and YouTube user dermetfan's LibgGdx tutorial sequence available at
 * http://www.youtube.com/watch?v=EJwXzmUQChg&feature=c4-overview-vl&list=PLXY8okVWvwZ0JOwHiH1TntAdq-UDPnC2L
 * @author Lisa Tagliaferri
 * @version 1.0
 */

package com.me.kitchencabinet.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.kitchencabinet.tween.ActorAccessor;

import aurelienribon.tweenengine.*;

public class Chips implements Screen {
	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table; 
	private TextButton backButton;
	private TweenManager tweenManager;

	public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw(); 
		
		tweenManager.update(delta);

	}

	public void resize(int width, int height) {
		
		stage.setViewport(width, height, true);
		table.invalidateHierarchy(); 
		table.setSize(width, height);

	}

	public void show() {
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage); 
		
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);
		
		Texture chipBg = new Texture(Gdx.files.internal("img/chips.png"));
		stage.addActor(new Image(chipBg));
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.bottom().right();
		
		backButton = new TextButton("BACK", skin);
		backButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new PotatoMenu());
			}
		});
		backButton.pad(10);

		table.add(backButton).bottom().right();;

		stage.addActor(table); 
	
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		tweenManager.update(Gdx.graphics.getDeltaTime());
		
		stage.addAction(sequence(moveTo(0, stage.getHeight()), moveTo(0, 0, .5f)));

	}

	public void hide() {
		
		dispose();

	}

	public void pause() {

	}

	public void resume() {

	}

	public void dispose() {
		
		stage.dispose();
		atlas.dispose();
		skin.dispose();

	}

}
