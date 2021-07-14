package com.xilda;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable
{
  private static final short CANVAS_WIDTH = 400, CANVAS_HEIGHT = 300;
  private static final byte CANVAS_SCALE = 2;

  private static final byte frame_rate = 60;

  private BufferedImage layer;
  private Thread main_loop;
  private JFrame frame;

  public void init_window()
  {
    this.setPreferredSize(new Dimension(getScaledWidth(), getScaledHeight()));

    this.layer = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
    this.frame = new JFrame("The Tale of Xilda");

    this.frame.add(this);
    this.frame.setResizable(false);
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.createBufferStrategy(3);
    this.frame.setVisible(true);
  }

  public synchronized void start()
  {
    this.main_loop = new Thread(this);
    main_loop.start();
  }

  public synchronized void stop()
  {
    this.main_loop.interrupt();
    this.frame.setVisible(false);
    this.frame.dispose();
  }

  private void update()
  {}

  private void render() {
    Graphics graphics = layer.getGraphics();
    graphics.setColor(Color.GRAY);
    graphics.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

    graphics.setColor(Color.RED);
    graphics.drawRect(15, 15, 20, 30);

    graphics.dispose();
    graphics = getBufferStrategy().getDrawGraphics();
    graphics.drawImage(layer, 0, 0, getScaledWidth(), getScaledHeight(), null);
    graphics.dispose();

    getBufferStrategy().show();
  }

  @Override
  public void run()
  {
    long last_time = System.nanoTime();
    double ns = 1_000_000_000D / this.frame_rate;

    while (true)
    {
      long now = System.nanoTime();
      Time.delta_time += (now - last_time) / ns;
      last_time = now;

      if (Time.delta_time > 1)
      {
        Time.update_fixed_delta_time();

        this.update();
        this.render();

        --Time.delta_time;
      }
    }
  }

  public static short getScaledWidth() {
    return CANVAS_WIDTH * CANVAS_SCALE;
  }

  public static short getScaledHeight() {
    return CANVAS_HEIGHT * CANVAS_SCALE;
  }
}
