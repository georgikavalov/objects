package com.musala.edu.objects.model;

/**
 * The {@code Triangle} class represents a triangle shape
 * 
 * @author georgi.kavalov
 *
 */
public class Triangle extends Shape {

	public Triangle(int sideSize, int startStreet, int startAvenue) {
		super(sideSize, startStreet, startAvenue);
	}

	/**
	 * Draws a triangle using a robot
	 */
	@Override
	public void draw(SophisticatedRobot painterRobot) {
		// go to start point
		painterRobot.goTo(startStreet, startAvenue, false);
		// paint side AB
		painterRobot.walkEW(-sideSize, true);
		// paint diagonally side BC
		painterRobot.walkNWSE(-sideSize, true);
		// paint side CA
		painterRobot.walkNS(-sideSize, true);
	}
}
