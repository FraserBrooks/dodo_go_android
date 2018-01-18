package com.fraserbrooks.game.state;

import android.view.MotionEvent;

import com.fraserbrooks.framework.util.Painter;
import com.fraserbrooks.dodo_go.Assets;

/**
 * Created by Fraser on 19/12/2017.
 */

public class LoadState extends State {

    @Override
    public void init() {
        Assets.load();
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Painter g) {
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
