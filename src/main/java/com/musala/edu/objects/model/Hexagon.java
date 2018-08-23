package com.musala.edu.objects.model;

public class Hexagon extends Shape {

	public Hexagon(int sideSize, int startStreet, int startAvenue) {
		super(sideSize, startStreet, startAvenue);
	}

	@Override
	public void draw(SophisticatedRobot painterRobot) {
		painterRobot.goTo(startStreet, startAvenue, false);

		painterRobot.walkEW(-sideSize, true);
		painterRobot.walkNESW(-sideSize, true);
		painterRobot.walkNWSE(-sideSize, true);
		painterRobot.walkEW(sideSize, true);
		painterRobot.walkNESW(sideSize, true);
		painterRobot.walkNWSE(sideSize, true);
	}
}
