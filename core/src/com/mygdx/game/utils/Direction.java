package com.mygdx.game.utils;

public enum Direction {
    UP(0, 1, 90.0f), DOWN(0, - 1, 270.0f), LEFT(-1,0, 180.0f), RIGHT(1, 0, 0.0f);

    private int vx;
    private int vy;
    private float angel;

    public int getVx() {
        return  vx;
    }

    public int getVy() {
        return  vy;
    }

    public float getAngel() {
        return angel;
    }

    Direction(int vx, int vy, float angel) {
        this.vx = vx;
        this.vy = vy;
        this.angel = angel;
    }


}
