package com.musala.edu.objects.model.robots;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kareltherobot.Directions;
import kareltherobot.KJRTest;
import kareltherobot.World;

/**
 * The {@code TestSophisticatedRobot} consists of unit tests for
 * {@link SophisticatedRobot}
 */
public class TestSophisticatedRobot extends KJRTest {
	private static final int PACES = 3;
	private static final int STREETS = 30;
	private static final int AVENUES = 30;
	private static final int ROBOT_STR_POS = 7;
	private static final int ROBOT_AVE_POS = 8;
	private SophisticatedRobot batka;

	@Before
	public void setup() {
		World.setSize(STREETS, AVENUES);
		World.setDelay(20);
		batka = new SophisticatedRobot(ROBOT_STR_POS, ROBOT_AVE_POS, Directions.North, Color.ORANGE);
		World.setVisible();
	}

	@After
	public void cleanup() {
		World.setVisible(false);
		World.reset();
		World.repaint();
	}

	@Test
	public void testTurningRight() {
		batka.turnRight();
		assertFacingEast(batka);
	}

	@Test
	public void testBasicMoves() {
		batka.circleBlockCounterClockwise(PACES, false);
		assertAt(batka, ROBOT_STR_POS, ROBOT_AVE_POS);
		batka.turnRight();
		batka.circleBlockClockwise(PACES, false);
		assertAt(batka, ROBOT_STR_POS, ROBOT_AVE_POS);
	}

	@Test
	public void testVerticalWalk() {
		batka.walkEW(-PACES, false);
		assertAt(batka, ROBOT_STR_POS, ROBOT_AVE_POS + PACES);
		batka.walkEW(PACES, false);
		assertAt(batka, ROBOT_STR_POS, ROBOT_AVE_POS);
	}

	@Test
	public void testHorizontalWalk() {
		batka.walkNS(-PACES, false);
		assertAt(batka, ROBOT_STR_POS - PACES, ROBOT_AVE_POS);
		batka.walkNS(PACES, false);
		assertAt(batka, ROBOT_STR_POS, ROBOT_AVE_POS);
	}

	@Test
	public void testUpwardDiagonalWalk() {
		batka.walkNESW(-PACES, false);
		assertAt(batka, ROBOT_STR_POS + PACES, ROBOT_AVE_POS + PACES);
		batka.walkNESW(PACES, false);
		assertAt(batka, ROBOT_STR_POS, ROBOT_AVE_POS);
	}

	@Test
	public void testDownwardDiagonalWalk() {
		batka.walkNWSE(PACES, false);
		assertAt(batka, ROBOT_STR_POS - PACES, ROBOT_AVE_POS + PACES);
		batka.walkNWSE(-PACES, false);
		assertAt(batka, ROBOT_STR_POS, ROBOT_AVE_POS);
	}
}