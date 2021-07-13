package com.xilda;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Game extends Canvas implements Runnable
{
  private JFrame frame;

  private final short CANVAS_WIDTH = 400, CANVAS_HEIGHT = 300;
  private final char CANVAS_SCALE = 2;

  private Thread main_loop;
  private final char frame_rate = 60;

  public void init_window()
  {
    /* THIS FUCKING STUPID IDE DECIDED TO INDENT    */
    /* THIS SHIT WITH DOUBLE THE SPACES AND I COULD */
    /* NOT FIX IT, SO FUCK IT! FORGET THIS CRAP,    */
    /* GLORY TO VIM!                                */
    this.setPreferredSize(
        new Dimension(
            CANVAS_WIDTH * CANVAS_SCALE,
            CANVAS_HEIGHT * CANVAS_SCALE
        )
    );

    this.frame = new JFrame("The Tale of Xilda");

    this.frame.add(this);
    this.frame.setResizable(false);
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setVisible(true);
  }

  public synchronized void start()
  {
    this.main_loop = new Thread(this);
    main_loop.start();
  }

  private void update()
  {}

  private void render()
  {}

  @Override
  public void run()
  {
    long last_time = System.nanoTime();
    double ns = 1000000000 / this.frame_rate;

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
