package com.musala.edu.objects.model.shapes;

import com.musala.edu.objects.model.robots.SophisticatedRobot;

/**
 * The {@code Rectangle} class represents a rectangular shape
 * 
 * @author georgi.kavalov
 *
 */
public class Rectangle extends Shape {

	private int width;
	private int length;

	public Rectangle(int width, int length, int startStreet, int startAvenue) {
		super(-1, startStreet, startAvenue);
		this.width = width;
		this.length = length;
	}

	/**
	 * Draws a rectangle using a robot
	 */
	@Override
	public void draw(SophisticatedRobot painterRobot) {
		// go to start point
		painterRobot.goTo(startStreet, startAvenue, false);

		painterRobot.walkEW(-length, true);
		painterRobot.walkNS(width, true);
		painterRobot.walkEW(length, true);
		painterRobot.walkNS(-width, true);
	}

	@Override
	public String getSizeDetailsString() {
		return String.format("size %d by %d", width, length);
	}
}
