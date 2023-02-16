package com.ggl.gear.demo;

import javax.swing.SwingUtilities;

import com.ggl.gear.demo.model.GearDemoModel;
import com.ggl.gear.demo.view.GearDemoFrame;

public class GearDemo implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new GearDemo());
	}

	@Override
	public void run() {
		new GearDemoFrame(new GearDemoModel());
	}

}
