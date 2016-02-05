/**SpriteAccessor utilised in Splash class for the tweenManager to move between values
 * Utilised YouTube user dermetfan's LibgGdx tutorial (Episode 4) avaliable at
 * http://www.youtube.com/watch?v=2PqwxYVlivA
 * @author Lisa Tagliaferri
 * @version 1.0
 */

package com.me.kitchencabinet.tween;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

/**SpriteAccessor implements TweenAccessor<Sprite> interface*/
public class SpriteAccessor implements TweenAccessor<Sprite> {

	/**ALPHA value is defined by the tweenType*/
	public static final int ALPHA = 0; //alpha value of the picture for the Splash class

	/**method shows what the values are at the moment (getter)*/
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		
		//fade-in
		switch(tweenType){
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1; //only one value declared --> return 1
		default:
			assert false; //if a tweenType is passed in that is not declared, it should not go through
			return -1; //to show something is wrong
		
		}
		
	}

	/**method sets values to how to change values (accessor)*/
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		
		//bring in values to fade to splash image
		switch(tweenType){
		case ALPHA:
			target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]); //pass in rgb at the moment, return newValues
			break;
		default:
			assert false;
		
		}
		
	}

}
