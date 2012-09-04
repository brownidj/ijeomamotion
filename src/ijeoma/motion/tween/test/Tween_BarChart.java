package ijeoma.motion.tween.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ijeoma.motion.*;
import ijeoma.motion.tween.*;
import processing.core.PApplet;

//based on Tween.js 01_bars.html example: http://sole.github.com/tween.js/examples/01_bars.html
public class Tween_BarChart extends PApplet {
	String[] cities = { "NYC", "LA", "Milan", "Venice", "London", "Amsterdam" };

	ArrayList<Bar> bars = new ArrayList<Bar>();

	float w = 100;
	float h = 100;
	float low = 250;
	float high = 1000;

	TweenSequence ts;
	float d = 75;

	public void setup() {
		size(800, 600);
		smooth();

		Motion.setup(this);

		setupBars();
	}

	void setupBars() {
		ts = new TweenSequence();

		bars.clear();

		for (int i = 0; i < cities.length; i++)
			bars.add(new Bar(cities[i], random(low, high), i * w, 0, w, 0));

		TweenParallel tp1 = new TweenParallel();
		for (int i = 0; i < bars.size(); i++) {
			float rh = bars.get(i).v / high * h;

			tp1.add(new Tween(d).add(bars.get(i), "h", -rh).setEasing(
					Tween.EXPO_OUT));
		}

		Collections.sort(bars, new ValueComparator());

		TweenParallel tp2 = new TweenParallel();
		for (int i = 0; i < bars.size(); i++)
			tp2.add((new Tween(d)).add(bars.get(i), "x", i * w).setEasing(
					Tween.EXPO_OUT));

		Collections.sort(bars, Collections.reverseOrder(new ValueComparator()));

		TweenParallel tp3 = new TweenParallel();
		for (int i = 0; i < bars.size(); i++)
			tp3.add((new Tween(d)).add(bars.get(i), "c",
					color(random(255), random(255), random(255))).setEasing(
					Tween.EXPO_OUT));

		TweenParallel tp4 = new TweenParallel();
		for (int i = 0; i < bars.size(); i++)
			tp4.add(new Tween(d).add(bars.get(i), "h", 1f).setEasing(
					Tween.EXPO_OUT));

		ts.add(tp1);
		ts.add(new Tween(d));
		ts.add(tp2);
		ts.add(new Tween(d));
		ts.add(tp3);
		ts.add(new Tween(d));
		ts.add(tp4);

		ts.play();
	}

	public void tweenSequenceEnded(TweenSequence ts) {
		println("tweenSequenceEnded");
		// setupBars();
	}

	public class ValueComparator implements Comparator<Bar> {
		public int compare(Bar o1, Bar o2) {
			return o1.v - o2.v;
		}
	}

	public void draw() {
		background(255);

		noStroke();

		pushMatrix();
		translate(width / 2 - (bars.size() * w) / 2, height / 2 + h / 2);
		for (Bar b : bars)
			b.draw();
		popMatrix();

		String time = (int) ts.getTime() + " / " + (int) ts.getDuration();

		fill(0);
		text(time, width - textWidth(time) - 10, height - 10);
	}

	public void keyPressed() {
		if (key == ' ')
			ts.play();
		else
			setupBars();
	}

	@Override
	public void mousePressed() {
		ts.pause();
	}

	@Override
	public void mouseReleased() {
		ts.resume();
	}

	public void mouseDragged() {
		ts.seek((float) mouseX / width);
	}

	class Bar {
		String name;
		int v;

		float x, y, w, h;
		int c; 

		Bar(String name, float value, float x, float y, float w, float h) {
			this.name = name;
			this.v = (int) value;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			c = color(random(255), random(255), random(255));
		}

		void draw() {
			noStroke();
			fill(c);
			rect(x, y, w, h);

			textAlign(CENTER);
			fill(255);
			text(v, x + w / 2, y - 10);
			fill(0);
			text(name, x + w / 2, y + 25);
		}
	}
}