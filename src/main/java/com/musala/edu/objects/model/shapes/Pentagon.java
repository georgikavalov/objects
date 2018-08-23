package com.musala.edu.objects.model.shapes;

import com.musala.edu.objects.model.robots.SophisticatedRobot;

/**
 * The {@code Pentagon} class represents a pentagonal shape
 * 
 * @author georgi.kavalov
 *
 */
public class Pentagon extends Shape {

	public Pentagon(int sideSize, int startStreet, int startAvenue) {
		super(sideSize, startStreet, startAvenue);
	}

	/**
	 * Draws a pentagon using a robot
	 */
	@Override
	public void draw(SophisticatedRobot painterRobot) {
		painterRobot.goTo(startStreet, startAvenue, false);

		painterRobot.walkEW(-sideSize, true);
		painterRobot.walkNESW(-(sideSize - 1), true);
		painterRobot.walkNWSE(-(sideSize + sideSize / 2 - 1), true);
		painterRobot.walkNESW(sideSize + sideSize / 2 - 1, true);
		painterRobot.walkNWSE(sideSize - sideSize % 2 - 1, true);
	}
}
