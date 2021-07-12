package com.xilda;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Game extends Canvas implements Runnable
{
  static JFrame frame;

  final int CANVAS_WIDTH = 400, CANVAS_HEIGHT = 300;
  final char CANVAS_SCALE = 2;

  void init_window()
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

  @Override
  public void run()
  {}
}