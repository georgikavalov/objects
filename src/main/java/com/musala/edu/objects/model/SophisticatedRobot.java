package com.musala.edu.objects.model;

import java.awt.Color;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kareltherobot.Robot;

/**
 * The {@code SophisticatedRobot} is a class that allows robot to execute more
 * sophisticated functions.
 * 
 * @author georgi.kavalov
 *
 */
public class SophisticatedRobot extends Robot {

	private static final Logger LOGGER = LoggerFactory.getLogger(SophisticatedRobot.class);
	private static final int BEEPERS = Integer.MAX_VALUE;
	private static final int CIRCLE_TURNS = 4;
	private int currentStreet;
	private int currentAvenue;

	public SophisticatedRobot(int street, int avenue, Direction direction) {
		super(street, avenue, direction, BEEPERS);
		initPosition(street, avenue);
	}

	public SophisticatedRobot(int street, int avenue, Direction direction, Color badgeColor) {
		super(street, avenue, direction, BEEPERS, badgeColor);
		initPosition(street, avenue);
	}

	private void initPosition(int street, int avenue) {
		this.currentStreet = street;
		this.currentAvenue = avenue;
	}

	/**
	 * Draws a square using beepers
	 * 
	 * @param startStreet
	 *            Staring point at street coordinate
	 * @param startAvenue
	 *            Starting point at avenue coordinate
	 * @param size
	 *            Size of a square side
	 */
	public void drawSquare(int startStreet, int startAvenue, int size) {
		goTo(startStreet, startAvenue);
		LOGGER.info("Drawing a square of {} from {}, {}", size, startStreet, startAvenue);
		circleCounterClockwise(size, true);
	}

	/**
	 * Makes robot to go to a position
	 * 
	 * @param startStreet
	 *            street coordinate
	 * @param startAvenue
	 *            avenue coordinate
	 */
	private void goTo(int startStreet, int startAvenue) {
		LOGGER.info("Going along to street {}", startStreet);
		walkEW(startStreet);
		LOGGER.info("Going across to avenue {}", startAvenue);
		walkNS(startAvenue);
	}

	/**
	 * Moves robot upward/downward an avenue
	 * 
	 * @param destAvenue
	 *            Coordinate to go to
	 */
	private void walkNS(int destAvenue) {
		int paces = destAvenue - currentAvenue;
		if (paces > 0) {
			pointNorth();
		} else {
			pointSouth();
		}
		walk(paces, false);
	}

	/**
	 * Moves robot left/right across streets
	 * 
	 * @param destStreet
	 *            Coordinate to go to
	 */
	private void walkEW(int destStreet) {
		int paces = destStreet - currentStreet;
		if (paces > 0) {
			pointEast();
		} else {
			pointWest();
		}
		walk(paces, false);
	}

	/**
	 * Turns robot right.
	 */
	public void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}

	/**
	 * Circles counter clockwise.
	 * 
	 * @param paces
	 *            Number of squares to move the robot.
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 */
	public void circleCounterClockwise(int paces, boolean leaveTrail) {
		circleAround(paces, this::turnLeft, leaveTrail);
	}

	/**
	 * Circles clockwise.
	 * 
	 * @param paces
	 *            Number of squares to move the robot.
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 */
	public void circleClockwise(int paces, boolean leaveTrail) {
		circleAround(paces, this::turnRight, leaveTrail);
	}

	/**
	 * Circles around in a given direction.
	 * 
	 * @param paces
	 *            Number of squares to move the robot.
	 * @param turningDireciton
	 *            A function for turning.
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 * 
	 */
	private void circleAround(int paces, Runnable turningDireciton, boolean leaveTrail) {
		pointEast();
		for (int i = 0; i < CIRCLE_TURNS; i++) {
			walk(paces, leaveTrail);
			turningDireciton.run();
		}
	}

	/**
	 * Makes robot walk in the direction it points and it may leave a trail of
	 * beepers or remove beepers along the way.
	 * 
	 * @param paces
	 *            Steps the robot will make.
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 */
	private void walk(int paces, boolean leaveTrail) {
		for (int j = 0; j < Math.abs(paces); j++) {
			makeAStep();
			if (leaveTrail) {
				this.putBeeper();
			} else if (this.nextToABeeper()) {
				this.pickBeeper();
			}
		}
	}

	/**
	 * Makes a step by updating its position and moves the robot one step ahead.
	 */
	private void makeAStep() {
		if (frontIsClear()) {
			if (this.facingEast()) {
				currentStreet++;
			} else if (this.facingNorth()) {
				currentAvenue++;
			} else if (this.facingSouth()) {
				currentAvenue--;
			} else if (this.facingWest()) {
				currentStreet--;
			}
			move();
		} else {
			LOGGER.warn("Could not go through obstruction.");
		}
	}

	/**
	 * Ensures the robot is facing East direction.
	 */
	private void pointEast() {
		turnUntilCondition(this::facingEast);
	}

	/**
	 * Ensures the robot is facing West direction.
	 */
	private void pointWest() {
		turnUntilCondition(this::facingWest);
	}

	/**
	 * Ensures the robot is facing North direction.
	 */
	private void pointNorth() {
		turnUntilCondition(this::facingNorth);
	}

	/**
	 * Ensures the robot is facing South direction.
	 */
	private void pointSouth() {
		turnUntilCondition(this::facingSouth);
	}

	/**
	 * Turns robot until a condition is met.
	 * 
	 * @param condition
	 *            Function that determines condition to terminate robot turning
	 */
	private void turnUntilCondition(Callable<Boolean> condition) {
		try {
			while (!condition.call()) {
				turnLeft();
			}
		} catch (Exception e) {
			LOGGER.error("Could not execute {}.", condition);
		}
	}
}
