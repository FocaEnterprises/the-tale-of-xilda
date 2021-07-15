package com.xilda;

import java.awt.Color;
import java.awt.Graphics;

public class Game
{
  private final Screen screen;
  private final Looping looping;

  public Game() {
    this.screen = new Screen("The Tale of Xilda");
    this.looping = new Looping(this::update, this::render);
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

  public void start() {
    looping.start();
  }

  @SuppressWarnings("unused")
  public void stop(int code) {
    looping.stop();
    System.exit(code);
  }
}
