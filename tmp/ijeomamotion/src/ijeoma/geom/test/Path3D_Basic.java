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

package ijeoma.geom.test;

import ijeoma.geom.Path2D;
import ijeoma.geom.Path3D;
import processing.core.PApplet;
import processing.core.PVector;

public class Path3D_Basic extends PApplet {
	int vertexCount = 5;
	float pathBegin, pathEnd, pathLength, pathSegmentLength;

	PVector[] vertices = new PVector[vertexCount];

	Path3D path;

	@Override
	public void setup() {
		size(300, 300, P3D);

		pathBegin = 0;
		pathEnd = width;
		pathLength = pathEnd - pathBegin;
		pathSegmentLength = pathLength / (vertexCount - 1);

		setupPath();
	}

	public void setupPath() {
		for (int i = 0; i < vertexCount - 1; i++) {
			float x = pathBegin + pathSegmentLength * i;
			float y = random(-75, 75);

			float z;

			if (i == 0 || i == vertexCount - 1)
				z = 0;
			else
				z = random(-100, 100);

			vertices[i] = new PVector(x, y, z);
		}

		vertices[vertexCount - 1] = new PVector(pathEnd, random(100, 200),
				random(0, 10));

		path = new Path3D(vertices);
	}

	@Override
	public void draw() {
		background(255);

		pushMatrix();
		translate(0, height / 2, 0);
		noFill();
		stroke(0);
		path.draw(g);

		PVector pathVertex = path.get((float) mouseX / width);

		pushMatrix();
		translate(pathVertex.x, pathVertex.y, pathVertex.z);
		noStroke();
		fill(0, 0, 0);
		sphere(5);
		popMatrix();

		popMatrix();
	}

	@Override
	public void keyPressed() {
		if (key == ' ')
			setupPath();
		else if (key == '1')
			path.setMode(Path2D.LINEAR);
		else if (key == '2')
			path.setMode(Path2D.COSINE);
		else if (key == '3')
			path.setMode(Path2D.CUBIC);
		else if (key == '4')
			path.setMode(Path2D.HERMITE);
	}
}