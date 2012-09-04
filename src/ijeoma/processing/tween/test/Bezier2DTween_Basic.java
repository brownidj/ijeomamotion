/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
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
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package ijeoma.processing.tween.test;

import ijeoma.motion.Motion;
import ijeoma.processing.geom.Bezier2D;
import ijeoma.processing.tween.Bezier2DTween;
import processing.core.PApplet;

public class Bezier2DTween_Basic extends PApplet {

	Bezier2D b1, b2;
	Bezier2DTween bt;

	@Override
	public void setup() {
		size(100, 100);
		smooth();

		b1 = new Bezier2D(g, 85, 20, 10, 10, 90, 90, 15, 80);
		b2 = new Bezier2D(g, 30, 20, 80, 5, 80, 75, 30, 75);

		Motion.setup(this);

		bt = new Bezier2DTween(b1, 0f, 1f, 100f);
		bt.play();
	}

	@Override
	public void draw() {
		background(255);

		noFill();

		bt.getBezier().draw();

		fill(0);
		ellipse(bt.getX(), bt.getY(), 10, 10);

		String time = bt.getTime() + " / " + bt.getDuration();
		fill(0);
		text(time, width - textWidth(time) - 10, height - 10);
	}

	@Override
	public void keyPressed() {
		bt.play();
	}

	public void tweenBezierEnded(Bezier2DTween _tb) {
		println("asdf");

		if (b1 == _tb.getBezier())
			_tb.setBezier(b2);
		else
			_tb.setBezier(b1);

		_tb.play();
	}
}