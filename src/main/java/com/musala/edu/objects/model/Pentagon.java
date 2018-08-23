package com.musala.edu.objects.model;

public class Pentagon extends Shape {

	public Pentagon(int sideSize, int startStreet, int startAvenue) {
		super(sideSize, startStreet, startAvenue);
	}

	@Override
	public void draw(SophisticatedRobot painterRobot) {
		painterRobot.goTo(startStreet, startAvenue, false);

		painterRobot.walkEW(-sideSize, true);
		painterRobot.walkNESW(-(sideSize - 1), true);
		painterRobot.walkNWSE(-(sideSize + sideSize / 2 - 1), true);
		painterRobot.walkNESW(sideSize + sideSize / 2 - 1, true);
		painterRobot.walkNWSE(sideSize - sideSize % 2 - 1, true);
	}
}
