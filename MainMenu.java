/**MainMenu class showing the first "Menu" screen of the Game Application
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

import com.me.kitchencabinet.screens.LevelMenu;
import com.me.kitchencabinet.tween.ActorAccessor;

import aurelienribon.tweenengine.*; //utilising BaseTween, Timeline, Tween, TweenCallback, TweenManager

import com.badlogic.gdx.*; //utilising Game, Gdx, Screen
import com.badlogic.gdx.graphics.*; //utilising GL20, Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*; //utilising Actor, InputEvent, Stage
import com.badlogic.gdx.scenes.scene2d.ui.*; //utilising Label, Skin, Table, TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**MainMenu class to implement Screen interface 
 */
public class MainMenu implements Screen {
	
	private Stage stage; //2d scene graph containing actors, handles viewport and input events
	private TextureAtlas atlas; //loads images from texture atlases
	private Skin skin; //stores resources for UI widgets to use, described in my created JSON file
	private Table table; //a group that sizes and positions children per constraints
	private TextButton buttonPlay, buttonAbout, buttonExit; //a button with a child Label to display text
	private Label heading; //a text label
	private TweenManager tweenManager; //updates all tweens and timelines and once
	/**tween -- core class of Tween Engine, interpolation between two values of an object attribute*/
	
	/**render() is called when the screen should render itself.
	 * @param delta - the time in seconds since the last render. 
	 */
	public void render (float delta) {
		
		/**clear screen prior to rendering new screen*/
		Gdx.gl.glClearColor(0, 0, 0, 1); //defines clear screen as black (setter)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears the screen (accessor)
		
		/**act method takes delta time since last frame, causes the act method
		 *on each actor to be called, so actors make this action based on time */
		stage.act(delta);
	
		/**draw method makes the stage visible */
		stage.draw(); 
		
		/**call for tween manager to update tweens and timelines*/
		tweenManager.update(delta);
		
	}
	
	/**resize() called when the Application is resized, at any point when at a non-paused state,
	 * except prior to create() call.
	 * @param width the new width in pixels
	 * @param height the new height in pixels 
	 */
	public void resize(int width, int height) {
		
		/**keep stage at same size*/
		stage.setViewport(width, height, true);
		
		/**let the layout of the table be recalculated*/
		table.invalidateHierarchy(); //invalidates table and its parents
		table.setSize(width, height); //set width and height of table when the window is resized by user
		
	}

	/**show() called when this screen becomes the current screen for a Game
	 */
	public void show() {
		
		/**instantiate & initialise stage */
		stage = new Stage();
	
		/**allow stage to get input events*/
		Gdx.input.setInputProcessor(stage);
		
		/** instantiate & initialise atlas and skin */
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);
		
		/**bring in PNG as a texture
		 * instantiate & initialise background image from file within assets (assets are added to the Android asset folder)*/
		Texture bg = new Texture(Gdx.files.internal("img/mainMenuBg.png"));
		/**add background image to stage so that it is visible*/
		stage.addActor(new Image(bg));
		
		/**instantiate & initialise table with skin */
		table = new Table(skin);
		/**set the initial table size as the size of the game per KitchenCabinet class*/
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		/**instantiate & initialise heading */
		heading = new Label("MENU", skin); //label takes in String and style from JSON
		/**scale font size of the heading*/
		heading.setFontScale(2); 

		/**instantiate & initialise buttons*/
	
		/**create buttonPlay and add ClickListener to go to new screen upon click*/
		buttonPlay = new TextButton("PLAY", skin); //text button takes String (word on button) and skin from JSON
		buttonPlay.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){ //change the look of the button when clicked based on JSON
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelMenu());
			}
	
		});
		buttonPlay.pad(15); //size of the button
		
		/**buttonAbout */
		buttonAbout = new TextButton("ABOUT", skin);
		buttonAbout.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new AboutMenu());
			}
	
		});
		buttonAbout.pad(15);
		
		/** buttonExit */
		buttonExit = new TextButton("EXIT", skin); //will read text button style out of the skin
		buttonExit.pad(15);
		buttonExit.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();

			}
		});
		buttonExit.pad(15);
		
		/**add all of the elements to the table in order of appearance */
		table.add(heading);
		table.getCell(heading).spaceBottom(100); //add space below element's cell
		table.row(); //adds a new row
		table.add(buttonPlay);
		table.getCell(buttonPlay).spaceBottom(15);
		table.row();
		table.add(buttonAbout);
		table.getCell(buttonAbout).spaceBottom(15);
		table.row();
		table.add(buttonExit); //last element, no need to add space
		
		/**add table to stage*/
		stage.addActor(table); //each element is considered to be an Actor
		
		/**instantiate & initialise tweenManager */
		tweenManager = new TweenManager();
		
		/**set up tween default configuration */
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		/**create sequence animation that has table elements drop in */
		Timeline.createSequence().beginSequence() //create sequential timeline, begin sequence
		
			/**set buttons that need to wait to an alpha transparency of 0
			* push = add a new animation to timeline */
			.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0)) 
			.push(Tween.set(buttonAbout, ActorAccessor.ALPHA).target(0)) 
			.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
			.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0)) //"from" opposite to "to", quarter second, from 0 to 1 
			
			/**change buttons to visible */
			.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .25f).target(1)) 
			.push(Tween.to(buttonAbout, ActorAccessor.ALPHA, .25f).target(1)) 
			.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .25f).target(1))
			.end().start(tweenManager); //end timeline
		
		/**table fades in */
		Tween.from(table, ActorAccessor.ALPHA, .5f).target(0); 
		Tween.from(table, ActorAccessor.Y, .5f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);//one-eighth up and down
		
		/**call for tweenManager to update based on delta time */
		tweenManager.update(Gdx.graphics.getDeltaTime());
		
	}

	/**hide() is called when this screen is no longer the current screen */
	public void hide() {
		
		/**dispose of current screen */
		dispose();
	
	}
	
	/**pause() is called when the Application is paused, before it is destroyed.
	 * On Android, a user pressing the Home button or receiving an incoming call.
	 * On desktop, this will be called only immediately before dispose() is called.
	 * An Application is paused before it is destroyed, when a user pressed the Home
	 */
	public void pause() {
	
	}
	
	/**resume() is called when the Application is resumed from a paused state. 
	 * On Android this happens when the activity regains focus 
	 * On the desktop this method will never be called. 
	 */
	public void resume() {
		
	}

	/**dispose() is called when the Application is destroyed.
	 * It is preceded by a call to pause().
	 */
	public void dispose() {
	
		/**dispose of elements on the screen */
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		
	}
}
