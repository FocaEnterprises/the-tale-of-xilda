package com.xilda.gameobjects;

import com.xilda.graphics.Screen;
import com.xilda.Vector2;

public class GameObject
{
  public Vector2 position;
  protected Vector2 scale;
  protected Screen screen;

  public GameObject(Screen screen)
  {
    this.screen = screen;
  }

  public void update()
  {}

  public void render(Screen screen)
  {}
}
