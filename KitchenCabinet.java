/**KitchenCabinet class is the class that inherits and implements the Game methods and initiates the 
 * game with the create() method.
 * KitchenCabinet also initiates the @param Strings TITLE and VERSION
 * Familiarisation of methods, classes, interfaces through ligdx wiki
 * available at https://code.google.com/p/libgdx/wiki/TableOfContents
 * and following tutorials "LibGdx" available at http://obviam.net/index.php/table-of-contents
 * and YouTube user dermetfan's LibgGdx tutorial sequence available at
 * http://www.youtube.com/watch?v=EJwXzmUQChg&feature=c4-overview-vl&list=PLXY8okVWvwZ0JOwHiH1TntAdq-UDPnC2L
 * @author Lisa Tagliaferri
 * @version 1.0
 */

package com.me.kitchencabinet;

import com.badlogic.gdx.*;

import com.me.kitchencabinet.screens.*;

public class KitchenCabinet extends Game {

	public static final String TITLE = "Kitchen Cabinet", VERSION = "1.0";
	
	/**inherits all methods from Game class, create() method is called once, and sets the screen */
	public void create() {		
		
		/**Screen to start the game with*/
		setScreen(new Splash());
		
	}

	/**inherits dispose() method to dispose of screen, calls inherited method*/
	public void dispose() {
		
		/**Calls dispose() method from Game class*/
		super.dispose(); 

	}

	/**inherits render() method to render the screen, calls inherited method*/
	public void render() {	
		
		/**Calls render() method from Game class*/
		super.render();

	}

	/**inherits resize() method to resize screen, calls inherited method*/
	public void resize(int width, int height) {
		
		/**Calls resize(width, height) method from Game class*/
		super.resize(width, height);

	}

	/**inherits pause() method to pause the screen, calls inherited method*/
	public void pause() {
		
		/**Calls pause() method from Game class*/
		super.pause();

	}

	/**inherits resume() method to resume the game, calls inherited method*/
	public void resume() {
		
		/**Calls resume() method from Game class*/
		super.resume();

	}
}
