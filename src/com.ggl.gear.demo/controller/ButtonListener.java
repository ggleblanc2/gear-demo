package com.ggl.gear.demo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class ButtonListener implements ActionListener {

	private final Timer timer;

	public ButtonListener(Timer timer) {
		this.timer = timer;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		String text = button.getText();

		if (text.equals("Start Gear Rotation")) {
			timer.restart();
			button.setText("Stop Gear Rotation");
		} else {
			timer.stop();
			button.setText("Start Gear Rotation");
		}
	}

}
