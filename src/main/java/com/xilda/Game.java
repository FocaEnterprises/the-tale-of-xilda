package com.xilda;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents the game
 */
public final class Game
{
  private final Screen screen;
  private final Loop loop;

  Game() {
    this.screen = new Screen("The Tale of Xilda");
    this.loop = new Loop(this::update, this::render);
  }

  /**
   * Game update logic called by loop update callback
   */
  private void update()
  {}

  /**
   * Game render logic called by loop render callback
   */
  private void render() {
    screen.startRender();

    Graphics graphics = screen.getGraphics();
    graphics.setColor(Color.RED);
    graphics.fillRect(50, 50, 20, 40);

    screen.endRender();
  }

  /**
   * Game start logic
   */
  public void start() {
    loop.start();
  }

  /**
   * Stops game loop
   */
  @SuppressWarnings("unused")
  public void stop(int code) {
    loop.stop();
    System.exit(code);
  }
}
