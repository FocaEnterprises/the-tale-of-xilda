package com.xilda;

public class Looping implements Runnable {

  private static final byte frame_rate = 60;

  private final Runnable updateCallback;
  private final Runnable renderCallback;

  private volatile boolean running;
  private Thread main_loop;

  public Looping(Runnable updateCallback, Runnable renderCallback) {
    this.updateCallback = updateCallback;
    this.renderCallback = renderCallback;
  }

  public synchronized void start()
  {
    this.main_loop = new Thread(this, "Looping thread");
    this.running = true;
    main_loop.start();
  }

  public synchronized void stop()
  {
    this.running = false;

    try {
      this.main_loop.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    long last_time = System.nanoTime();
    long now;

    double ns = 1_000_000_000D / Looping.frame_rate;

    while (running)
    {
      now = System.nanoTime();
      Time.delta_time += (now - last_time) / ns;
      last_time = now;

      if (Time.delta_time > 1)
      {
        Time.update_fixed_delta_time();

        this.updateCallback.run();
        this.renderCallback.run();

        --Time.delta_time;
      }
    }
  }
}
