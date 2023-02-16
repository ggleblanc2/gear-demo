package com.ggl.gear.demo.controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ggl.gear.demo.model.ColorPair;
import com.ggl.gear.demo.model.Gear;
import com.ggl.gear.demo.model.GearDemoModel;
import com.ggl.gear.demo.view.GearDemoFrame;

public class GearListener extends MouseAdapter {

	private final GearDemoFrame view;

	private final GearDemoModel model;

	private JComboBox<ColorPair> colorPairComboBox;

	private Point centerPoint;

	public GearListener(GearDemoFrame view, GearDemoModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) {
			Gear gear = model.getGear(event.getPoint());
			if (gear == null) {
				centerPoint = event.getPoint();
			} else {
				centerPoint = null;
			}
		} else if (event.getButton() == MouseEvent.BUTTON3) {
			Gear originalGear = model.getGear(event.getPoint());
			Gear gear = model.getGear(event.getPoint(), 1);
			if (gear == null && originalGear == null) {
				model.removeGears();
			} else if (gear != null) {
				model.removeGear(gear);
			}

			view.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) {
			Gear gear = model.getGear(event.getPoint());
			if (gear != null && centerPoint != null) {
				Point point = gear.getCenterPoint();
				double distance = Point.distance(centerPoint.x, centerPoint.y,
						point.x, point.y);
				double radius = distance - gear.getShortRadius();

				Color gearColor = Color.BLACK;
				Color spotColor = Color.YELLOW;
				int value = JOptionPane.showConfirmDialog(view.getFrame(),
						createOptionPanel(), "Choose Gear Color",
						JOptionPane.YES_NO_OPTION);
				if (value == JOptionPane.YES_OPTION) {
					ColorPair colorPair = (ColorPair) colorPairComboBox
							.getSelectedItem();
					gearColor = colorPair.getGearColor();
					spotColor = colorPair.getSpotColor();
				}

				Gear newGear = new Gear(centerPoint, gearColor, spotColor,
						radius, !gear.isClockwise());
				model.addGear(newGear);
				view.repaint();
			}
		}
	}

	private JPanel createOptionPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		colorPairComboBox = new JComboBox<>(model.getColorPairs());
		panel.add(colorPairComboBox);

		return panel;
	}

}
