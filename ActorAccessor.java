/**ActorAccessor utilised by Actors in Tables and Stages for the tweenManager to animate
 * Utilised YouTube user dermetfan's LibgGdx tutorial (Episode 8) avaliable at
 * http://www.youtube.com/watch?v=2PqwxYVlivA
 * @author Lisa Tagliaferri
 * @version 1.0
 */

package com.me.kitchencabinet.tween;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

public class ActorAccessor implements TweenAccessor<Actor>{
	
	/**declare Y, RGB, and ALPHA values to animate along Y-axis @param Y
	 * and fade-in and out @param ALPHA
	 */
	public static final int Y = 0, ALPHA = 1;
	
	/**getter*/
	public int getValues(Actor target, int tweenType, float[] returnValues) {
		
		switch(tweenType){
		case Y:
			returnValues[0] = target.getY();return 1;
		
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		default:
			assert false;
			return - 1;
		}
		
	}
	
	/**setter*/
	public void setValues(Actor target, int tweenType, float[] newValues) {
		
		switch(tweenType){
		case Y:
			target.setY(newValues[0]);
			break;
		
		case ALPHA:
			target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
			break;
		default:
			assert false;
		}
		
	}

}
