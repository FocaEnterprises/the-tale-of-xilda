package com.xilda;

public class Time
{
  public static double delta_time;
  public static double fixed_delta_time;

  public static void update_fixed_delta_time()
  {
    if (fixed_delta_time == 0)
      fixed_delta_time = delta_time;

    fixed_delta_time = (fixed_delta_time + delta_time) / 2;
  }
}
