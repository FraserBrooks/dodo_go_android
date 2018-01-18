package com.fraserbrooks.game.state;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.fraserbrooks.dodo_go.GameMainActivity;
import com.fraserbrooks.framework.util.Painter;
import com.fraserbrooks.dodo_go.Assets;

/**
 * Created by Fraser on 19/12/2017.
 */

class MenuState extends State {

    // Rect object for each button
    private Rect playRect;
    private Rect scoreRect;

    // booleans for pressed or not
    private boolean playDown = false;
    private boolean scoreDown = false;

    @Override
    public void init() {

        int buttonLeft = GameMainActivity.GAME_WIDTH/ 2 - 160;
        int buttonRight = GameMainActivity.GAME_WIDTH/ 2 + 160;
        int buttonHeight = GameMainActivity.GAME_HEIGHT / 14;

        playRect = new Rect( buttonLeft,GameMainActivity.GAME_HEIGHT / 3,
                            buttonRight,GameMainActivity.GAME_HEIGHT / 3 + buttonHeight);
        scoreRect = new Rect(buttonLeft, playRect.top + (buttonHeight * 3) / 2 ,
                            buttonRight,playRect.top + (buttonHeight * 3) / 2 + buttonHeight);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome, 0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);

        if(playDown){
            g.drawImage(Assets.startDown, playRect.left, playRect.top, playRect.width(), playRect.height());
        } else {
            g.drawImage(Assets.start, playRect.left, playRect.top, playRect.width(), playRect.height());
        }

        if(scoreDown){
            g.drawImage(Assets.scoreDown, scoreRect.left, scoreRect.top, scoreRect.width(), scoreRect.height());
        } else {
            g.drawImage(Assets.score, scoreRect.left, scoreRect.top, scoreRect.width(), scoreRect.height());
        }
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if (e.getAction() == MotionEvent.ACTION_DOWN){
            if (playRect.contains(scaledX, scaledY)){
                playDown = true;
                scoreDown = false;
            }
        } else if (scoreRect.contains(scaledX, scaledY)){
            scoreDown = true;
            playDown = false;
        }

        if (e.getAction() == MotionEvent.ACTION_UP){
            if (playDown && playRect.contains(scaledX, scaledY)){
                playDown = false;
                Log.d("MenuState", "Play button released");
                setCurrentState(new PlayState());
            } else if (scoreDown && scoreRect.contains(scaledX, scaledY)){
                scoreDown = false;
                //TODO
                Log.d("MenuState", "Score button released");
            } else {
                scoreDown = false;
                playDown = false;
            }
        }

        return true;
    }
}
