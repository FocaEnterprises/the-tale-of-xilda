/* ____  ___.___.____     ________      _____    */
/* \   \/  /|   |    |    \______ \    /  _  \   */
/*  \     / |   |    |     |    |  \  /  /_\  \  */
/*  /     \ |   |    |___  |    `   \/    |    \ */
/* /___/\  \|___|_______ \/_______  /\____|__  / */
/*       \_/            \/        \/         \/  */

// Authors:   gnevesdev, GiverPlay007, Akira
// Date:      2021
// Languages: Java
// License:   See LICENSE file

package com.xilda;

public class Main
{
  public static void main(String[] args)
  {
    Game game = new Game();

    Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
      System.out.println("Unhandled exception in thread " + t.getName());
      e.printStackTrace();

      game.stop(1);
    });

    game.init_window();
    game.start();
  }
}
