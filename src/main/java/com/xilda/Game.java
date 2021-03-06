package com.xilda;

import com.xilda.gameobjects.Player;
import com.xilda.graphics.Screen;

/**
 * Represents the game
 */
public final class Game
{
  private final Screen screen;
  private final Loop loop;

  private final Player player;

  Game() {
    this.screen = new Screen("The Tale of Xilda");
    this.loop = new Loop(this::update, this::render);

    this.player = new Player(this.screen);
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

    this.player.render(this.screen);

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
