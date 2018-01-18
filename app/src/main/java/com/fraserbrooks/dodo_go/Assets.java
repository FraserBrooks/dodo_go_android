package com.fraserbrooks.dodo_go;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.AudioManager;
import android.media.SoundPool;

import com.fraserbrooks.framework.animation.Animation;
import com.fraserbrooks.framework.animation.Frame;

/**
 * Created by Fraser on 19/12/2017.
 */

public class Assets {

    private static SoundPool soundPool;
    public static Bitmap welcome, block,cloud1, cloud2, duck, grass, jump, run1,
            run2, run3, run4, run5, run6, run7, run8, run9, run10, run11, scoreDown,
            score, startDown, start;

    public static Animation runAnim, jumpAnim;

    public static int hitID, onJumpID;

    public static void load(){
        welcome = loadBitmap("welcome.png", false);
        block = loadBitmap("block.png", false);
        cloud1 = loadBitmap("cloud1.png", true);
        cloud2 = loadBitmap("cloud2.png", true);
        duck = loadBitmap("dodo_duck.png", true);
        grass = loadBitmap("grass.png", false);
        jump = loadBitmap("dodo_jump.png", true);
        run1 = loadBitmap("dodo_run_1.png", true);
        run2 = loadBitmap("dodo_run_2.png", true);
        run3 = loadBitmap("dodo_run_3.png", true);
        run4 = loadBitmap("dodo_run_4.png", true);
        run5 = loadBitmap("dodo_run_5.png", true);
        run6 = loadBitmap("dodo_run_6.png", true);
        run7 = loadBitmap("dodo_run_7.png", true);
        run8 = loadBitmap("dodo_run_8.png", true);
        run9 = loadBitmap("dodo_run_9.png", true);
        run10 = loadBitmap("dodo_run_10.png", true);
        run11 = loadBitmap("dodo_run_11.png", true);
        scoreDown = loadBitmap("score_button_down.png", true);
        score = loadBitmap("score_button.png", true);
        startDown = loadBitmap("start_button_down.png", true);
        start = loadBitmap("start_button.png", true);

        Frame f1 = new Frame(run1, .05f);
        Frame f2 = new Frame(run2, .05f);
        Frame f3 = new Frame(run3, .05f);
        Frame f4 = new Frame(run4, .05f);
        Frame f5 = new Frame(run5, .05f);
        Frame f6 = new Frame(run6, .05f);
        Frame f7 = new Frame(run7, .05f);
        Frame f8 = new Frame(run8, .05f);
        Frame f9 = new Frame(run9, .05f);
        Frame f10 = new Frame(run10, .05f);
        Frame f11 = new Frame(run11, .05f);

        Frame f12 = new Frame(duck, .07f);
        Frame f13 = new Frame(jump, 99f);

        runAnim = new Animation(f1,f2,f3,f4,f5,f5,f6,f7,f8,f9,f10,f11);
        jumpAnim = new Animation(f12,f13);

        hitID = loadSound("hit.wav");
        onJumpID = loadSound("onJump.wav");
    }

    private static Bitmap loadBitmap(String filename, boolean transparency){
        InputStream inputStream = null;
        try{
            inputStream = GameMainActivity.assets.open(filename);
        } catch (IOException e){
            e.printStackTrace();
        }
        Options options = new Options();
        if (transparency){
            options.inPreferredConfig = Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Config.RGB_565;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        return bitmap;
    }

    private static int loadSound(String filename){
        int soundID = 0;
        if (soundPool == null){
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
        }
        try{
            soundID = soundPool.load(GameMainActivity.assets.openFd(filename),1);
        } catch (IOException e){
            e.printStackTrace();
        }
        return soundID;
    }

    public static void playSound(int soundID){
        soundPool.play(soundID, 1,1,1,0,1);
    }

}
