package com.core;

public class Settings {
    public static int MAX_TILES;
    public static double FPS;
    public static int WIDTH;
    public static int HEIGHT;
    public static int PLAYERS;
    public static double STAND_PADDING;

    // tiles config
    public static double TRACKTILE_WIDTH_BASE;
    public static double TRACKTILE_HEIGHT_BASE;
    public static double OCTATILE_HEIGHT_BASE;
    public static double OCTATILE_WIDTH_BASE;
    public static double TILE_SIZE_SCALE;
    
    // chicken config
    public static double CHICKEN_WIDTH_BASE;
    public static double CHICKEN_HEIGHT_BASE;
    public static double CHICKEN_SIZE_SCALE;

    public static double CHICKEN_PADDING_X;
    public static double CHICKEN_PADDING_Y;

    static {
        MAX_TILES = 12;
        FPS = 30;
        WIDTH = 1000;
        HEIGHT = 700;
        PLAYERS = 4;
        STAND_PADDING = 12.0f;

        TRACKTILE_HEIGHT_BASE = 25.0f;
        TRACKTILE_WIDTH_BASE = 21.0f;
        TILE_SIZE_SCALE = 2f;

        OCTATILE_HEIGHT_BASE = 27.0f;
        OCTATILE_WIDTH_BASE = 27.0f;

        CHICKEN_WIDTH_BASE = 64.0f;
        CHICKEN_HEIGHT_BASE = 64.0f;
        CHICKEN_SIZE_SCALE = 1f;

        CHICKEN_PADDING_Y = (CHICKEN_HEIGHT_BASE * CHICKEN_SIZE_SCALE - TRACKTILE_HEIGHT_BASE * TILE_SIZE_SCALE) + (TRACKTILE_HEIGHT_BASE * TILE_SIZE_SCALE)/3;
        CHICKEN_PADDING_X = (CHICKEN_WIDTH_BASE * CHICKEN_SIZE_SCALE - TRACKTILE_WIDTH_BASE * TILE_SIZE_SCALE)/2;
    }
}
