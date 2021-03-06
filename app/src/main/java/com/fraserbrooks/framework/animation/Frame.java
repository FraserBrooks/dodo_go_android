package com.fraserbrooks.framework.animation;

import android.graphics.Bitmap;

/**
 * Created by Fraser on 20/12/2017.
 */

public class Frame {
    private Bitmap image;
    private double duration;

    public Frame(Bitmap image, double duration){
        this.image = image;
        this.duration = duration;
    }

    public double getDuration(){
        return duration;
    }

    public Bitmap getImage(){
        return image;
    }
}
