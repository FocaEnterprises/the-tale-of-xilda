package com.xilda.graphics;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Window wrapper
 */
public class Screen {
  public static final short WIDTH = 400;
  public static final short HEIGHT = 300;
  public static final byte SCALE = 2;

  private final BufferedImage layer;
  private final Canvas canvas;

  private Graphics graphics;
  private boolean rendering;

  /**
   * @param title JFrame window title
   */
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

  /**
   * Start render process creating graphics and filling background
   * @see Screen#endRender
   * @throws IllegalStateException if screen is already rendering
   */
  public void startRender() {
    if(rendering) {
      throw new IllegalStateException("Render already started.");
    }

    rendering = true;
    graphics = layer.getGraphics();
    graphics.setColor(Color.black);
    graphics.fillRect(0, 0, WIDTH, HEIGHT);
  }

  /**
   * Finalize render process drawing layer into canvas Graphics
   * @see Screen#startRender
   * @throws IllegalStateException if screen is not rendering yet
   */
  public void endRender() {
    if(!rendering) {
      throw new IllegalStateException("Not rendering yet.");
    }

    Graphics screenGraphics = canvas.getBufferStrategy().getDrawGraphics();
    screenGraphics.drawImage(layer, 0, 0, getScaledWidth(), getScaledHeight(), null);

    canvas.getBufferStrategy().show();
    graphics.dispose();
    screenGraphics.dispose();
    rendering = false;
  }

  /**
   * Get screen width scaled
   * @return screen width times screen scale
   */
  public static int getScaledWidth() {
    return WIDTH * SCALE;
  }

  /**
   * Get screen height scaled
   * @return screen height times screen scale
   */
  public static int getScaledHeight() {
    return HEIGHT * SCALE;
  }

  /**
   * Get Graphics provided by Screen#renderStart
   * @see Screen#startRender
   * @see Screen#endRender
   * @return current Graphics
   */
  public Graphics getGraphics() {
    return graphics;
  }

  public final byte get_scale()
  {
    return this.SCALE;
  }
}
