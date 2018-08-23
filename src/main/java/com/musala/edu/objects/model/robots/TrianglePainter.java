package com.musala.edu.objects.model.robots;

import com.musala.edu.objects.model.shapes.Triangle;

public class TrianglePainter extends SophisticatedRobot implements Painter {

	private Triangle triangle;

	public TrianglePainter(Triangle triangle) {
		super();
		this.triangle = triangle;
	}

	@Override
	public void draw() {
		triangle.draw(this);
	}
}
