package com.xilda;

import java.awt.Color;
import java.awt.Graphics;

public class Game implements Runnable
{
  private static final byte frame_rate = 60;
  private final Screen screen;

  private Thread main_loop;

  public Game() {
    screen = new Screen("The Tale of Xilda");
  }

  public synchronized void start()
  {
    this.main_loop = new Thread(this);
    main_loop.start();
  }

  public synchronized void stop()
  {
    if(this.main_loop.isAlive()) {
      this.main_loop.interrupt();
    }
  }

  private void update()
  {}

  private void render() {
    screen.startRender();

    Graphics graphics = screen.getGraphics();
    graphics.setColor(Color.RED);
    graphics.fillRect(50, 50, 20, 40);

    screen.endRender();
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
}
