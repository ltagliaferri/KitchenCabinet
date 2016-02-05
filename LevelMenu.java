/**LevelMenu class showing the "Levels" menu to take user to game play once user selects
 * a city and presses the "PLAY" button.
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

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelMenu implements Screen {
	
	private Stage stage;
	private Table table, tableBottom;
	private TextureAtlas atlas;
	private Skin skin;
	private List list; //or "list box" displays textual items and highlights the currently selected item
	private ScrollPane scrollPane; //group that scrolls a child widget using scroll bars
	private TextButton cook, back;

	public void render(float delta) {
	
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();

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
		
		Texture levelBg = new Texture(Gdx.files.internal("img/levelMain.png"));
		stage.addActor(new Image(levelBg));
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		/** instantiate & initialise list comprised of a String array to populate list
		 * Strings here are city locations in order of appearance on the episodes of Kitchen Cabinet, 
		 * broadcast throughout the UK
		 */
		list = new List(new String[] {"Sibton", "Whitechapel", "Melton Mowbray", "Rye", "Bristol", "Brighton", "Southwold", "Newcastle", "Cumbria", "Edinburgh", "Birmingham", "Bath"}, skin);
		
		/** instantiate & initialise scollPane, with list widget as child, and using skin
		 * as described in JSON
		 */
		scrollPane = new ScrollPane(list, skin);
		
		cook = new TextButton("COOK", skin);
		cook.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new Cook());
			}
		});
		cook.pad(15);
		
		back = new TextButton("BACK", skin);
		back.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});
		back.pad(10);
		
		/** instantiate & initialise image textBox, with instructions of game play */
		Texture textBox = new Texture(Gdx.files.internal("img/text.png"));
		
		table.add().width(table.getWidth() / 5); //make table have 5 columns for spacing
		table.add().width(table.getWidth() / 5).row(); //add row, for spacing / style
		table.add(scrollPane).left().expandY(); //put scroll pane on left, allow for it to expand as more cities are added
		table.add(cook);
		table.add(new Image(textBox)).right(); // put textBox image to the right
		
		/** instantiate & initialise tableBottom at the bottom right for back button */
		tableBottom = new Table(skin);
		tableBottom.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		tableBottom.bottom();
		tableBottom.right();
		
		tableBottom.add(back).bottom().right();
		
		
		stage.addActor(table);
		stage.addActor(tableBottom);
		
		/** animation for stage to drop in within a half second */
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
