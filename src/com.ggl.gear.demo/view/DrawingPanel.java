package com.ggl.gear.demo.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.ggl.gear.demo.controller.GearListener;
import com.ggl.gear.demo.model.Gear;
import com.ggl.gear.demo.model.GearDemoModel;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final GearDemoModel model;

	public DrawingPanel(GearDemoFrame view, GearDemoModel model) {
		this.model = model;
		this.setPreferredSize(model.getDrawingArea());
		this.addMouseListener(new GearListener(view, model));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		
		for (Gear gear : model.getGears()) {
			BufferedImage image = gear.getImage();
			
			double rotationRequired = Math.toRadians(gear.getDisplayAngle());
			double locationX = image.getWidth() / 2;
			double locationY = image.getHeight() / 2;
			AffineTransform tx = AffineTransform
					.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_BILINEAR);

			Point p = gear.getCenterPoint();
			int x = p.x - image.getWidth() / 2;
			int y = p.y - image.getHeight() / 2;
			g2d.drawImage(op.filter(image, null), x, y, this);
		}
	}

}
