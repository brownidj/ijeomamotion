/**
 * ijeomamotion
 * A collection of utilities creating flash-like animations.
 * http://ekeneijeoma.com/processing/ijeomamotion/
 *
 * Copyright (C) 2012 Ekene Ijeoma http://ekeneijeoma.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      Ekene Ijeoma http://ekeneijeoma.com
 * @modified    08/21/2012
 * @version     4 (26)
 */

package ijeoma.motion.timeline.test;

import processing.core.PApplet;
import processing.core.PFont;

import ijeoma.motion.*;
import ijeoma.motion.timeline.*;

public class Timeline_Callback_Basic extends PApplet {
	PFont font;

	Timeline tl;

	String word = "";

	public void setup() {
		size(400, 200);

		ellipseMode(CORNER);
		smooth();

		font = createFont("Arial", 50);
		textFont(font);

		Motion.setup(this);

		tl = new Timeline();
		// Timeline.addCallback(String _callbackObjectMethodName, float _time,
		// float _duration)
		tl.add(new Callback("zero"), 0);
		tl.add(new Callback("one"), 100);
		tl.add(new Callback("two"), 200);
		tl.add(new Callback("three"), 300);
		tl.add(new Callback("four"), 400);
		tl.add(new Callback("five"), 500);
		tl.repeat().play();
	}

	public void draw() {
		background(255);

		fill(0);
		textFont(font, 36);
		text(word, width / 2 - textWidth(word) / 2, height / 2 + 36 / 2);

		textFont(font, 12);
		String time = (int) tl.getTime() + " / " + (int) tl.getDuration();
		text(time, width - textWidth(time) - 10, height - 10);
	}

	public void zero() {
		word = "ZERO";
		println(word);
	}

	public void one() {
		word = "ONE";
		println(word);
	}

	public void two() {
		word = "TWO";
		println(word);
	}

	public void three() {
		word = "THREE";
		println(word);
	}

	public void four() {
		word = "FOUR";
		println(word);
	}

	public void five() {
		word = "FIVE";
		println(word);
	}

	public void keyPressed() {
		tl.play();
	}

	public void mousePressed() {
		tl.pause();
	}

	public void mouseReleased() {
		tl.resume();
	}

	public void mouseDragged() {
		tl.seek(norm(mouseX, 0, width));
	}
}
