package com.xilda.gameobjects;

import com.xilda.Screen;
import com.xilda.Vector2;

import java.awt.*;

public class Player extends GameObject
{
  private Vector2 velocity;
  private float speed;

  public Player()
  {
    this.position = new Vector2(50, 50);
    this.velocity = new Vector2(0, 0);

    this.speed = 4.f;
  }

  @Override public void update()
  {
    /* Nao sei como fazer o input :P
    if (key_right) {
      velocity.x += speed * Time.delta_time;
    }
    */

    this.position.x += this.velocity.x;
    this.position.y += this.velocity.y;
  }

  @Override public void render(Screen screen)
  {
    Graphics graphics = screen.getGraphics();
    graphics.setColor(Color.RED);
    graphics.fillRect((int)this.position.x, (int)this.position.y, 20, 40);
  }
}
