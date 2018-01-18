package com.fraserbrooks.game.model;

/**
 * Created by Fraser on 20/12/2017.
 */


import android.graphics.Rect;

import com.fraserbrooks.framework.util.RandomNG;
import com.fraserbrooks.game.state.PlayState;;

public class Block {
    private float x, y;
    private int width, height;
    private Rect rect;
    private boolean visible;

    private static final int UPPER_Y = 520;
    private static final int LOWER_Y = 670;

    private PlayState playState;

    public Block(float x, float y, int width, int height, PlayState playState){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playState = playState;
        rect = new Rect((int) x, (int) y, (int) x + width, (int) y + height);
        visible = false;
    }

    public void update(float delta, float velX){
        x += velX * delta;
        if (x <= -50){
            reset();
        }
        updateRect();
    }

    public void updateRect(){
        rect.set((int) x, (int) y, (int) x + width, (int) y + height);
    }

    public void reset(){
        if(visible){
            playState.increaseScore();
        }
        visible = true;
        if(RandomNG.getRandInt(3) == 0){
            y = UPPER_Y;
        } else{
            y = LOWER_Y;
        }
        x += 2500;
    }

    public void onCollide(Player p){
        visible = false;
        p.pushBack(6000);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public boolean isVisible(){
        return visible;
    }

    public Rect getRect(){
        return rect;
    }

}
