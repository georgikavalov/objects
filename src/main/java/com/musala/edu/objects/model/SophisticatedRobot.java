package com.musala.edu.objects.model;

import java.awt.Color;

import kareltherobot.Robot;

/**
 * The {@code SophisticatedRobot} is a class that allows robot to execute more
 * sophisticated functions.
 * 
 * @author georgi.kavalov
 *
 */
public class SophisticatedRobot extends Robot {

	private static final int BEEPERS = Integer.MAX_VALUE;
	private static final int CIRCLE_TURNS = 4;

	public SophisticatedRobot(int street, int avenue, Direction direction) {
		super(street, avenue, direction, BEEPERS);
	}

	public SophisticatedRobot(int street, int avenue, Direction direction, Color badgeColor) {
		super(street, avenue, direction, BEEPERS, badgeColor);
	}

	/**
	 * Turns right.
	 */
	public void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}

	/**
	 * Circles counter clockwise.
	 */
	public void circleCounterClockwise() {
		circleAround(this::turnLeft);
	}

	/**
	 * Circles clockwise.
	 */
	public void circleClockwise() {
		circleAround(this::turnRight);
	}

	/**
	 * Circles around in a given direction.
	 * 
	 * @param turningDireciton
	 *            A function for turning
	 */
	private void circleAround(Runnable turningDireciton) {
		pointEast();
		for (int i = 0; i < CIRCLE_TURNS; i++) {
			move();
			turningDireciton.run();
		}
	}

	/**
	 * Ensures the robot is facing East direction.
	 */
	private void pointEast() {
		while (!this.facingEast()) {
			turnLeft();
		}
	}
}
