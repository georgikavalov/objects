package com.musala.edu.objects.model.shapes;

import com.musala.edu.objects.model.robots.SophisticatedRobot;

/**
 * The {@code Shape} is an abstract class encapsulating common properties of a
 * shape concrete object.
 * 
 * @author georgi.kavalov
 *
 */
public abstract class Shape {

	protected int sideSize;
	protected int startStreet;
	protected int startAvenue;

	public Shape(int sideSize, int startStreet, int startAvenue) {
		this.sideSize = sideSize;
		this.startStreet = startStreet;
		this.startAvenue = startAvenue;
	}

	/**
	 * Draws a shape, given a robot that does sophisticated moves
	 * 
	 * @param painterRobot
	 *            A {@link SophisticatedRobot} object that can move and paint the
	 *            shape in question
	 */
	public abstract void draw(SophisticatedRobot painterRobot);

	public String getSizeDetailsString() {
		return String.format("size %d", sideSize);
	}

	public int getSize() {
		return sideSize;
	}

	public int getStartStreet() {
		return startStreet;
	}

	public int getStartAvenue() {
		return startAvenue;
	}
}
