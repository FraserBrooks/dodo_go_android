package com.fraserbrooks.framework.util;

import java.util.Random;

/**
 * Created by Fraser on 20/12/2017.
 */

public class RandomNG {
    private static Random rand = new Random();

    public static int getRandR(int lower, int higher){
        return rand.nextInt(higher - lower) + lower;
    }

    public static int getRandInt(int upperBound) {
        return rand.nextInt(upperBound);
    }

}
