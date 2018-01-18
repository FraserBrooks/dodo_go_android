package com.fraserbrooks.game.model;

/**
 * Created by Frase on 20/12/2017.
 */

import com.fraserbrooks.framework.util.RandomNG;;

public class Cloud {
    private float x, y;
    private static final int VEL_X = -15;

    public Cloud(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void update(float delta){
        x += VEL_X * delta;

        if (x <= -200){
            x += 2000;
            y = RandomNG.getRandR(40,  200);
        }
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

}
