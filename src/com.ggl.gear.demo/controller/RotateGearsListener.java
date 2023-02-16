package com.ggl.gear.demo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ggl.gear.demo.model.Gear;
import com.ggl.gear.demo.model.GearDemoModel;
import com.ggl.gear.demo.view.GearDemoFrame;

public class RotateGearsListener implements ActionListener {
	
	private final GearDemoFrame view;
	
	private final GearDemoModel model;

	public RotateGearsListener(GearDemoFrame view, GearDemoModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		for (Gear gear : model.getGears()) {
			gear.incrementDisplayAngle();
		}
		
		view.repaint();
	}

}
