package com.musala.edu.objects.model;

import java.awt.Color;
import java.util.Random;

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
	private static final int PACES = 1;
	private static final int STREETS = 30;
	private static final int AVENUES = 30;
	private static final int START_AVENUE = 2;
	private static final int START_STREET = 2;
	private SophisticatedRobot batka;

	@Before
	public void setup() {
		World.setSize(STREETS, AVENUES);
		World.setDelay(20);
		batka = new SophisticatedRobot(START_STREET, START_AVENUE, Directions.North, Color.ORANGE);
		World.setVisible();
	}

	@After
	public void cleanup() {
		World.reset();
	}

	@Test
	public void testTurningRight() {
		batka.turnRight();
		assertFacingEast(batka);
	}

	@Test
	public void testBasicMoves() {
		batka.circleCounterClockwise(PACES, false);
		assertAt(batka, START_STREET, START_AVENUE);
		batka.turnRight();
		batka.circleClockwise(PACES, false);
		assertAt(batka, START_STREET, START_AVENUE);
	}

	@Test
	public void testDrawingSquare() {
		final int squareSize = new Random().nextInt(STREETS - START_STREET - 1);
		batka.drawSquare(START_STREET + 2, START_AVENUE + 4, squareSize);
		assertBeepersAt(START_AVENUE + 4, START_STREET + 2);// corner A
		assertBeepersAt(START_AVENUE + 4, START_STREET + 2 + squareSize);// corner B
		assertBeepersAt(START_AVENUE + 4 + squareSize, START_STREET + 2 + squareSize); // corner C
		assertBeepersAt(START_AVENUE + 4 + squareSize, START_STREET + 2); // corner D
		assertBeepersInWorld(4 * squareSize);
	}
}