package com.musala.edu.objects.model.shapes;

import com.musala.edu.objects.model.robots.SophisticatedRobot;

/**
 * The {@code Rectangle} class represents a hexagonal shape
 * 
 * @author georgi.kavalov
 *
 */
public class Hexagon extends Shape {

	public Hexagon(int sideSize, int startStreet, int startAvenue) {
		super(sideSize, startStreet, startAvenue);
	}

	/**
	 * Draws a hexagon using a robot
	 */
	@Override
	public void draw(SophisticatedRobot painterRobot) {
		painterRobot.goTo(startStreet, startAvenue, false);

		painterRobot.walkEW(-sideSize, true);
		painterRobot.walkNESW(-sideSize, true);
		painterRobot.walkNWSE(-sideSize, true);
		painterRobot.walkEW(sideSize, true);
		painterRobot.walkNESW(sideSize, true);
		painterRobot.walkNWSE(sideSize, true);
	}
}
