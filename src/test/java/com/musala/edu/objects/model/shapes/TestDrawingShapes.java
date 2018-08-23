package com.musala.edu.objects.model.shapes;

import java.awt.Color;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musala.edu.objects.model.robots.SophisticatedRobot;

import kareltherobot.Directions;
import kareltherobot.KJRTest;
import kareltherobot.World;

/**
 * The {@code TestDrawingShapes} consists of unit tests for {@link Shape}
 * objects, particularly its abstract draw() method
 */
public class TestDrawingShapes extends KJRTest {

	private static final int STREETS = 30;
	private static final int AVENUES = 30;
	private static final int START_AVENUE = 10;
	private static final int START_STREET = 12;
	private static final int ROBOT_STR_POS = 7;
	private static final int ROBOT_AVE_POS = 8;
	private SophisticatedRobot picasso;

	@Before
	public void setup() {
		World.setSize(STREETS, AVENUES);
		World.setDelay(20);
		picasso = new SophisticatedRobot(ROBOT_STR_POS, ROBOT_AVE_POS, Directions.North, Color.ORANGE);
		World.setVisible();
	}

	@After
	public void cleanup() {
		World.setVisible(false);
		World.reset();
		World.repaint();
	}

	@Test
	public void testDrawingSquare() {
		final int squareSize = new Random().nextInt(STREETS - START_STREET - 1) + 1;
		picasso.drawShape(new Square(squareSize, START_STREET, START_AVENUE));
		assertBeepersAt(START_AVENUE, START_STREET);// corner A
		assertBeepersAt(START_AVENUE, START_STREET + squareSize);// corner B
		assertBeepersAt(START_AVENUE + squareSize, START_STREET + squareSize); // corner C
		assertBeepersAt(START_AVENUE + squareSize, START_STREET); // corner D
		assertBeepersInWorld(4 * squareSize);
	}

	@Test
	public void testDrawingTraiangle() {
		final int triangleSide = new Random().nextInt((STREETS - START_STREET - 1) / 2) + 1;
		picasso.drawShape(new Triangle(triangleSide, START_STREET, START_AVENUE));
		assertBeepersAt(START_AVENUE, START_STREET);// corner A
		assertBeepersAt(START_AVENUE, START_STREET + triangleSide);// corner B
		assertBeepersAt(START_AVENUE + triangleSide, START_STREET); // corner C
		assertBeepersInWorld(3 * triangleSide);
	}

	@Test
	public void testDrawingRectangle() {
		final int rectWidth = new Random().nextInt((STREETS - START_STREET - 1)) + 1;
		final int rectLength = new Random().nextInt((AVENUES - START_AVENUE - 1)) + 1;

		picasso.drawShape(new Rectangle(rectWidth, rectLength, START_STREET, START_AVENUE));
		assertBeepersInWorld(2 * rectWidth + 2 * rectLength);
	}

	@Test
	public void testDrawingPentagon() {
		final int pentagonSize = new Random().nextInt((STREETS - START_STREET - 1) / 2) + 2;

		picasso.drawShape(new Pentagon(pentagonSize, START_STREET, START_AVENUE));
		assertBeepersInWorld(6 * pentagonSize - 2 * (pentagonSize % 2) - 4);// It is based on how many beepers are left
																			// on trail. Refer to draw method in the
																			// Pentagon class
	}

	@Test
	public void testDrawingHexagon() {
		final int hexagonSize = new Random().nextInt((STREETS - START_STREET - 1) / 2) + 2;

		picasso.drawShape(new Hexagon(hexagonSize, START_STREET, START_AVENUE));
		assertBeepersInWorld(6 * hexagonSize);
	}
}
