package com.xilda.gameobjects;

import com.xilda.graphics.Screen;
import com.xilda.Vector2;
import com.xilda.graphics.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject
{
  private Vector2 velocity;
  private float speed;

  private Spritesheet animation;
  private BufferedImage sprite;

  public Player(Screen screen)
  {
    super(screen);
    this.position = new Vector2(50, 50);
    this.scale = new Vector2(16 * screen.get_scale(), 16 * screen.get_scale());

    this.velocity = new Vector2(0, 0);
    this.speed = 4.f;

    this.animation = new Spritesheet("assets/sprites/player.png");
    this.sprite = this.animation.get_sprite(0, 0, 16, 16);
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
    graphics.fillRect(
      (int)this.position.x,
      (int)this.position.y,
      (int)this.scale.x,
      (int)this.scale.y
    );

    graphics.drawImage(
      this.sprite,
      (int)this.position.x,
      (int)this.position.y,
      (int)this.scale.x,
      (int)this.scale.y,
      null
    );
  }
}
