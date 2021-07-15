package com.xilda;

public class Loop implements Runnable {

  private static final byte frame_rate = 60;

  private final Runnable updateCallback;
  private final Runnable renderCallback;

  private volatile boolean isRunning;
  private Thread main_loop;

  /**
   * <strong>Game Loop</strong>
   * <p>
   *   Loop calls update callback and render callback at fixed frame rate
   * </p>
   * @param updateCallback Called on game loop update
   * @param renderCallback Called on game loop render
   */
  public Loop(Runnable updateCallback, Runnable renderCallback) {
    this.updateCallback = updateCallback;
    this.renderCallback = renderCallback;
  }

  /**
   * Starts the game loop thread
   * @throws IllegalStateException if the loop already started
   */
  public synchronized void start()
  {
    if(isRunning) {
      throw new IllegalStateException("Game loop already started.");
    }

    this.main_loop = new Thread(this, "Loop thread");
    this.isRunning = true;
    main_loop.start();
  }

  /**
   * Await game loop thread join
   * @throws IllegalStateException if game loop is not running
   */
  public synchronized void stop()
  {
    if(!isRunning) {
      throw new IllegalStateException("Game loop is not running.");
    }

    this.isRunning = false;

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

    double ns = 1_000_000_000D / Loop.frame_rate;

    while (isRunning)
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
