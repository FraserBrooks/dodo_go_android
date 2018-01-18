package com.fraserbrooks.game.state;

/**
 * Created by Frase on 20/12/2017.
 */


import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.fraserbrooks.dodo_go.GameMainActivity;
import com.fraserbrooks.framework.util.Painter;

public class GameOverState extends State {

    private String playerScore;

    public GameOverState(int playerScore){
        this.playerScore = playerScore + "";
    }

    @Override
    public void init() {
        //
    }

    @Override
    public void update(float delta) {
        // Do Nothing

    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.rgb(225,145,0));
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        g.setColor(Color.DKGRAY);
        g.setFont(Typeface.DEFAULT_BOLD, 75);
        g.drawString(" GAME OVER", GameMainActivity.GAME_WIDTH / 2 - 200, GameMainActivity.GAME_HEIGHT / 4);
        g.drawString(" Touch to Exit", GameMainActivity.GAME_WIDTH / 2 - 200, 3 * GameMainActivity.GAME_HEIGHT / 4);
        g.setFont(Typeface.DEFAULT_BOLD, 250);
        g.drawString(" " + playerScore, GameMainActivity.GAME_WIDTH / 2 - 200, GameMainActivity.GAME_HEIGHT / 2);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_UP){
            setCurrentState(new MenuState());
        }
        return true;
    }


}