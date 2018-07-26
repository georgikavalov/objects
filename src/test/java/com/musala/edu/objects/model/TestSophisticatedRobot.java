package com.musala.edu.objects.model;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kareltherobot.Directions;
import kareltherobot.KJRTest;
import kareltherobot.World;

/**
 * The {@code TestBasicRobot} consists of unit tests for
 * {@link SophisticatedRobot}
 */
public class TestSophisticatedRobot extends KJRTest {
	private static final int START_AVENUE = 2;
	private static final int START_STREET = 2;
	private SophisticatedRobot batka;

	@Before
	public void setup() {
		batka = new SophisticatedRobot(START_STREET, START_AVENUE, Directions.North, Color.ORANGE);
		World.setDelay(20);
		World.setVisible();
	}

	@After
	public void cleanup() {
		World.resetRobots();
	}

	@Test
	public void testTurningRight() {
		batka.turnRight();
		assertFacingEast(batka);
	}

	@Test
	public void testBasicMoves() {
		batka.circleCounterClockwise();
		assertAt(batka, START_STREET, START_AVENUE);
		batka.turnRight();
		batka.circleClockwise();
		assertAt(batka, START_STREET, START_AVENUE);
	}
}