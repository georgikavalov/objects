package com.musala.edu.objects.model;

/**
 * The {@code Square} class represents a square shape
 * 
 * @author georgi.kavalov
 *
 */
public class Square extends Shape {

	public Square(int sideSize, int startStreet, int startAvenue) {
		super(sideSize, startStreet, startAvenue);
	}

	/**
	 * Draws a square using a robot
	 */
	@Override
	public void draw(SophisticatedRobot painterRobot) {
		painterRobot.goTo(startStreet, startAvenue, false);
		painterRobot.circleBlockCounterClockwise(sideSize, true);
	}
}
