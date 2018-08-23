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

	public void drawShape(Shape shape) {
		LOGGER.info("Drawing a {} of {} from {}, {}", shape.getClass().getSimpleName(), shape.getSizeDetailsString(),
				shape.getStartStreet(), shape.getStartAvenue());
		shape.draw(this);
	}

	/**
	 * Makes robot to go to a position
	 * 
	 * @param street
	 *            street coordinate
	 * @param avenue
	 *            avenue coordinate
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 */
	public void goTo(int street, int avenue, boolean leaveTrail) {
		LOGGER.info("Going along to street {}", street);
		walkEW(currentStreet - (street - 1), leaveTrail);
		LOGGER.info("Going across to avenue {}", avenue);
		walkNS((avenue + 1) - currentAvenue, leaveTrail);
	}

	/**
	 * Makes robot walk in a diagonal NorhtWest to SouthEast or in reverse
	 * 
	 * @param paces
	 *            Number of diagonal paces. Sign of integer indicates direction
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 */
	public void walkNWSE(int paces, boolean leaveTrail) {
		int forward = paces > 0 ? 1 : -1;
		for (int i = 0; i < Math.abs(paces); i++) {
			walkNS(-forward * 1, false);
			walkEW(-forward * 1, leaveTrail);
		}
	}

	/**
	 * Makes robot walk in a diagonal NorthEast to SouthWest or in reverse
	 * 
	 * @param paces
	 *            Number of diagonal paces. Sign of integer indicates direction
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 */
	public void walkNESW(int paces, boolean leaveTrail) {
		int forward = paces < 0 ? 1 : -1;
		for (int i = 0; i < Math.abs(paces); i++) {
			walkNS(forward * 1, false);
			walkEW(-forward * 1, leaveTrail);
		}
	}

	/**
	 * Moves robot North to South or in reverse
	 * 
	 * @param paces
	 *            Number of vertical paces. Sign indicates direction
	 */
	public void walkNS(int paces, boolean leaveTrail) {
		if (paces > 0) {
			pointNorth();
		} else if (paces < 0) {
			pointSouth();
		}
		walk(paces, leaveTrail);
	}

	/**
	 * Moves robot East to West or in reverse
	 * 
	 * @param paces
	 *            Number of horizontal paces. Sign indicates direction
	 */
	public void walkEW(int paces, boolean leaveTrail) {
		if (paces > 0) {
			pointWest();
		} else if (paces < 0) {
			pointEast();
		}
		walk(paces, leaveTrail);
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
	 * Circles counter clockwise around a block.
	 * 
	 * @param paces
	 *            Number of squares to move the robot.
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 */
	public void circleBlockCounterClockwise(int paces, boolean leaveTrail) {
		circleBlockAround(paces, this::turnLeft, leaveTrail);
	}

	/**
	 * Circles clockwise around a block.
	 * 
	 * @param paces
	 *            Number of squares to move the robot.
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 */
	public void circleBlockClockwise(int paces, boolean leaveTrail) {
		circleBlockAround(paces, this::turnRight, leaveTrail);
	}

	/**
	 * Circles around a block in a given direction.
	 * 
	 * @param paces
	 *            Number of squares to move the robot.
	 * @param turningDireciton
	 *            A function for turning.
	 * @param leaveTrail
	 *            A flag indicating if it is going to leave a trail.
	 * 
	 */
	private void circleBlockAround(int paces, Runnable turningDireciton, boolean leaveTrail) {
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
