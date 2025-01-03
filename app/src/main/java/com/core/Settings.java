package com.core;

public class Settings {
    public static int MAX_TILES;
    public static double FPS;
    public static int WIDTH;
    public static int HEIGHT;
    public static int PLAYERS;
    public static double STAND_PADDING;

    static {
        MAX_TILES = 12;
        FPS = 30;
        WIDTH = 1000;
        HEIGHT = 700;
        PLAYERS = 4;
        STAND_PADDING = 12.0f;
    }
}
