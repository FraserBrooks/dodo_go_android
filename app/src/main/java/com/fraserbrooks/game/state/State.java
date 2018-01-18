package com.fraserbrooks.game.state;


import android.view.MotionEvent;

import com.fraserbrooks.framework.util.Painter;
import com.fraserbrooks.dodo_go.GameMainActivity;

/**
 * Created by Fraser on 19/12/2017.
 */

public abstract class State {

    public void setCurrentState(State newState){
        GameMainActivity.sGame.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);

}
