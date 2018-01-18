package com.fraserbrooks.dodo_go;

import android.app.Activity;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.view.WindowManager;

public class GameMainActivity extends Activity {

    public static final int GAME_WIDTH = 2000;
    public static final int GAME_HEIGHT = 900;
    public static GameView sGame;
    public static AssetManager assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assets = getAssets();
        sGame = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
        setContentView(sGame);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

}
