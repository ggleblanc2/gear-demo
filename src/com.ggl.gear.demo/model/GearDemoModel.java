package com.ggl.gear.demo.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GearDemoModel {
	
	private final ColorPair[] colorPairs;
	
	private final Dimension drawingArea;
	
	private final List<Gear> gears;
	
	public GearDemoModel() {
		int width = 1600;
		int height = 900;
		this.drawingArea = new Dimension(width, height);
		this.gears = new ArrayList<>();
		this.gears.add(new Gear(new Point(width / 2, height / 2), Color.BLACK,
				Color.YELLOW, 160, true));
		this.colorPairs = generateColorPairs();
	}
	
	private ColorPair[] generateColorPairs() {
		ColorPair[] colorPairs = new ColorPair[4];
		colorPairs[0] = new ColorPair("Blue", Color.BLUE, Color.YELLOW);
		colorPairs[1] = new ColorPair("Gray", Color.GRAY, Color.WHITE);
		colorPairs[2] = new ColorPair("Magenta", Color.MAGENTA, Color.WHITE);
		colorPairs[3] = new ColorPair("Red", Color.RED, Color.YELLOW);
		
		return colorPairs;
	}
	
	public void removeGears() {
		for (int index = gears.size() - 1; index > 0; index--) {
			gears.remove(index);
		}
	}
	
	public void removeGear(Gear gear) {
		gears.remove(gear);
	}
	
	public void addGear(Gear gear) {
		gears.add(gear);
	}

	public ColorPair[] getColorPairs() {
		return colorPairs;
	}

	public Dimension getDrawingArea() {
		return drawingArea;
	}
	
	public Gear getGear(Point point) {
		return getGear(point, 0);
	}
	
	public Gear getGear(Point point, int startingIndex) {
		for (int index = startingIndex; index < gears.size(); index++) {
			Gear gear = gears.get(index);
			Point centerPoint = gear.getCenterPoint();
			double radius = gear.getRadius();
			double distance = Point.distance(centerPoint.x, centerPoint.y,
					point.x, point.y);
			if (distance <= radius) {
				return gear;
			}
		}

		return null;
	}

	public List<Gear> getGears() {
		return gears;
	}

}
