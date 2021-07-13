package com.xilda;

public class Time
{
  public static double delta_time;
  public static double fixed_delta_time;

  public static void update_fixed_delta_time()
  {
    if (fixed_delta_time == 0)
      fixed_delta_time = delta_time;

    final double AVERAGE_DELTA_TIME = (fixed_delta_time + delta_time) / 2;
    fixed_delta_time = AVERAGE_DELTA_TIME;
  }
}
