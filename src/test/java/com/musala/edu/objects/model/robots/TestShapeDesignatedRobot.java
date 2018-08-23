package com.musala.edu.objects.model.robots;

import org.junit.Test;

import com.musala.edu.objects.model.robots.Painter;
import com.musala.edu.objects.model.robots.TrianglePainter;
import com.musala.edu.objects.model.shapes.Triangle;

public class TestShapeDesignatedRobot {

	@Test
	public void testTrianglePainter() {
		Painter painter = new TrianglePainter(new Triangle(3, 5, 5));
		painter.draw();
	}
}
