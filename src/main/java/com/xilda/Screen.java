package com.xilda;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Screen {
  public static final int WIDTH = 400;
  public static final int HEIGHT = 300;
  public static final int SCALE = 2;

  private final BufferedImage layer;
  private final Canvas canvas;

  private Graphics graphics;

  public Screen(String title) {
    layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(getScaledWidth(), getScaledHeight()));

    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(canvas);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);

    canvas.createBufferStrategy(3);
    frame.setVisible(true);
  }

  public void startRender() {
    graphics = layer.getGraphics();
    graphics.setColor(Color.black);
    graphics.fillRect(0, 0, WIDTH, HEIGHT);
  }

  public void endRender() {
    Graphics screenGraphics = canvas.getBufferStrategy().getDrawGraphics();
    screenGraphics.drawImage(layer, 0, 0, getScaledWidth(), getScaledHeight(), null);

    canvas.getBufferStrategy().show();

    graphics.dispose();
    screenGraphics.dispose();
  }

  public static int getScaledWidth() {
    return WIDTH * SCALE;
  }

  public static int getScaledHeight() {
    return HEIGHT * SCALE;
  }

  public Graphics getGraphics() {
    return graphics;
  }
}
