package com.ggl.gear.demo.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class Gear {

	private final boolean isClockwise;

	private int numberOfTeeth;

	private final double radius;
	private double displayAngle, shortRadius, toothArc;

	private final BufferedImage image;

	private final Color gearColor, spotColor;

	private final Point centerPoint;

	public Gear(Point centerPoint, Color gearColor, Color spotColor,
			double radius, boolean isClockwise) {
		this.centerPoint = centerPoint;
		this.gearColor = gearColor;
		this.spotColor = spotColor;
		this.radius = radius;
		this.isClockwise = isClockwise;
		this.displayAngle = 0.0;
		this.image = createImage();
	}

	private BufferedImage createImage() {
		int margin = 5;
		int r = (int) Math.round(radius);
		int width = r + r + margin + margin;
		int height = width;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		
		// Make background transparent
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, width, height);
		
		// Calculate the number of teeth and gaps
		int toothDepth = 14;
		int toothWidth = 20;
		double circumference = 2.0 * Math.PI * radius;
		int teethAndGaps = (int) Math.floor(circumference / toothWidth);
		// Make sure there are an even number of teeth and gaps
		teethAndGaps -= (teethAndGaps % 2 == 0) ? 0 : 1;
		this.numberOfTeeth = teethAndGaps / 2;
		this.toothArc = 360.0 / teethAndGaps;
		
		// Create gear shaft
		int tinyRadius = 20;
		Polygon polygon = new Polygon();
		int x = 0, y = 0;
		for (int angle = -269; angle <= 90; angle++) {
			double radians = Math.toRadians(angle);
			x = (int) (Math.round(Math.sin(radians) * tinyRadius)) + width / 2;
			y = (int) (Math.round(Math.cos(radians) * tinyRadius)) + height / 2;
			polygon.addPoint(x, y);
		}
		
		// Create gear
		shortRadius = radius - toothDepth;
		int x2 = width / 2 + r + (int) Math.round(shortRadius);
		polygon.addPoint(x2, y);
		boolean isShortRadius = true;
		for (int index = 0; index < teethAndGaps; index++) {
			double currentRadius = (isShortRadius) ? shortRadius : radius;
			double angle = toothArc * index + 90.0;
			double radians = Math.toRadians(angle);
			int x1 = (int) (Math.round(Math.sin(radians) * currentRadius))
					+ width / 2;
			int y1 = (int) (Math.round(Math.cos(radians) * currentRadius))
					+ height / 2;
			polygon.addPoint(x1, y1);

			angle = toothArc * (index + 1) + 90.0;
			radians = Math.toRadians(angle);
			x1 = (int) (Math.round(Math.sin(radians) * currentRadius))
					+ width / 2;
			y1 = (int) (Math.round(Math.cos(radians) * currentRadius))
					+ height / 2;
			polygon.addPoint(x1, y1);

			isShortRadius = !isShortRadius;
		}
		
		// Draw gear
		g2d.setColor(gearColor);
		g2d.fillPolygon(polygon);

		// Draw spot
		int x3 = width / 2 + r / 2;
		g2d.setColor(spotColor);
		int r2 = 10;
		g2d.fillOval(x3 - r2, (height / 2) - r2, r2 + r2, r2 + r2);

		g2d.dispose();
		
		return image;
	}

	public boolean isClockwise() {
		return isClockwise;
	}

	public int getNumberOfTeeth() {
		return numberOfTeeth;
	}

	public double getShortRadius() {
		return shortRadius;
	}

	public double getRadius() {
		return radius;
	}
	
	public void incrementDisplayAngle() {
		double increment = toothArc * 0.25;
		this.displayAngle += (isClockwise) ? increment : -increment;
	}

	public void setDisplayAngle(double displayAngle) {
		this.displayAngle = displayAngle;
	}

	public double getDisplayAngle() {
		return displayAngle;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Color getGearColor() {
		return gearColor;
	}

	public Color getSpotColor() {
		return spotColor;
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

}
