package com.fraserbrooks.game.model;

/**
 * Created by Frase on 20/12/2017.
 */

import android.graphics.Rect;

import com.fraserbrooks.dodo_go.Assets;

public class Player {
    private float x, y;
    private int width, height, velY;
    private Rect rect, duckRect, ground;

    private boolean isAlive;
    private boolean isDucked;
    private float duckDuration = 1f;

    private static final int JUMP_VELOCITY = -860;
    private static final int ACCEL_GRAVITY = 1700;

    public Player(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        ground = new Rect(0, 810, 2000, 2000);
        rect = new Rect();
        duckRect = new Rect();
        isAlive = true;
        isDucked = false;
        updateRects();
    }

    public void update(float delta){

        if (duckDuration > 0 && isDucked){
            duckDuration -= delta;
        } else {
            isDucked = false;
            duckDuration = .6f;
        }

        if (!isGrounded()) {
            Assets.jumpAnim.update(delta);
            velY += ACCEL_GRAVITY * delta;
        } else {
            Assets.jumpAnim.reset();;
            y = 812 - (height - 40);
            velY = 0;
        }

        y += velY * delta;
        updateRects();

    }

    public void updateRects(){
        //rect.set((int) x + 80, (int) y + 40, (int) x + 80 + (width-120), (int) y + 40 + height - 80);
        rect.set((int) x + 80, (int) y + 40, (int) x + width - 40, (int) y + height - 40);
        //duckRect.set((int) x + 80,(int) y + 80, (int) x + 80 + width-120, (int) y + 80 + height - 160);
        duckRect.set((int) x + 80,(int) y + 80, (int) x + width - 40, (int) y + height - 80);
    }

    public void jump(){
        if (isGrounded()){
            Assets.playSound(Assets.onJumpID);
            isDucked = false;
            duckDuration = .6f;
            y -= 20;
            velY = JUMP_VELOCITY;
            updateRects();
        }
    }

    public void duck(){
        if (isGrounded()){
            isDucked = true;
        }
    }

    public void pushBack(int dX){
        Assets.playSound(Assets.hitID);
        x -= dX;
        if (x < -width / 2){
            isAlive = false;
        }
        rect.set((int) x, (int) y, (int) x + width, (int) y + height);
    }

    public boolean isGrounded(){
        return Rect.intersects(rect, ground);
    }

    public boolean isDucked(){
        return isDucked;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getVelY(){
        return velY;
    }

    public Rect getRect(){
        return rect;
    }

    public Rect getDuckRect(){
        return duckRect;
    }

    public Rect getGround(){
        return ground;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public float getDuckDuration(){
        return duckDuration;
    }

}
