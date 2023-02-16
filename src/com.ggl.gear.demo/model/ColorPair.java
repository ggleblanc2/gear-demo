package com.ggl.gear.demo.model;

import java.awt.Color;

public class ColorPair {
	
	private final Color gearColor, spotColor;
	
	private final String text;

	public ColorPair(String text, Color gearColor, Color spotColor) {
		this.text = text;
		this.gearColor = gearColor;
		this.spotColor = spotColor;
	}

	public Color getGearColor() {
		return gearColor;
	}

	public Color getSpotColor() {
		return spotColor;
	}

	@Override
	public String toString() {
		return text;
	}
}
