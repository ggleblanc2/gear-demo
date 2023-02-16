package com.ggl.gear.demo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.ggl.gear.demo.controller.ButtonListener;
import com.ggl.gear.demo.controller.RotateGearsListener;
import com.ggl.gear.demo.model.GearDemoModel;

public class GearDemoFrame {
	
	private final DrawingPanel drawingPanel;
	
	private final JFrame frame;
	
	private final Timer timer;

	public GearDemoFrame(GearDemoModel model) {
		this.drawingPanel = new DrawingPanel(this, model);
		this.timer = new Timer(25, new RotateGearsListener(this, model));
		this.frame = createAndShowGUI();
	}

	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Gear Demonstration");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				timer.stop();
				frame.dispose();
				System.exit(0);
			}
		});
		
		frame.add(drawingPanel, BorderLayout.CENTER);
		frame.add(createControlPanel(), BorderLayout.SOUTH);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		return frame;
	}
	
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JButton button = new JButton("Start Gear Rotation");
		button.addActionListener(new ButtonListener(timer));
		panel.add(button);
		
		return panel;
	}
	
	public void repaint() {
		drawingPanel.repaint();
	}

	public JFrame getFrame() {
		return frame;
	}
	
}
