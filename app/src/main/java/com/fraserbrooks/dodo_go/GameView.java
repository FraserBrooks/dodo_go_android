package com.fraserbrooks.dodo_go;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

import com.fraserbrooks.framework.util.InputHandler;
import com.fraserbrooks.framework.util.Painter;
import com.fraserbrooks.game.state.State;
import com.fraserbrooks.game.state.LoadState;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

/**
 * Created by Fraser on 19/12/2017.
 */

public class GameView extends SurfaceView implements Runnable {

    private Bitmap gameImage;
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    private Painter graphics;

    private Thread gameThread;
    private volatile boolean running;
    private volatile State currentState;

    private InputHandler inputHandler;

    public GameView(Context context, int gameWidth, int gameHeight){
        super(context);
        gameImage = Bitmap.createBitmap(gameWidth, gameHeight, Bitmap.Config.RGB_565);
        gameImageSrc = new Rect(0, 0, gameImage.getWidth(), gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage);
        graphics = new Painter(gameCanvas);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Log.d("GameView", "Surface Created");
                initInput();
                if (currentState == null){
                    setCurrentState(new LoadState());
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                //TODO
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.d("GameView", "Surface Destroyed");
                pauseGame();
            }
        });
    }


    public GameView(Context context){
        super(context);
    }

    public void setCurrentState(State newState) {
        System.gc();
        newState.init();
        currentState = newState;
        inputHandler.setCurrentState(currentState);
    }

    private void initInput(){
        if(inputHandler == null){
            inputHandler = new InputHandler();
        }
        setOnTouchListener(inputHandler);
    }

    @Override
    public void run() {
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        while (running){
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis + updateDurationMillis;
            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;
            sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);

            try{
                Thread.sleep(sleepDurationMillis);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void initGame(){
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    private void pauseGame(){
        running = false;
        while(gameThread.isAlive()){
            try{
                gameThread.join();
                break;
            } catch (InterruptedException e) {
            }
        }
    }

    private void updateAndRender(long delta){
        currentState.update(delta / 1000f);
        currentState.render(graphics);
        renderGameImage();
    }

    private void renderGameImage(){
        Canvas screen = getHolder().lockCanvas();
        if(screen != null){
            screen.getClipBounds(gameImageDst);
            if(gameImageDst.width() > gameImageDst.height()){
                screen.drawBitmap(gameImage, gameImageSrc,gameImageDst, null);
            } else{
                gameImageSrc.set(gameImage.getWidth()/2 - gameImage.getHeight()/2,
                                  0,
                                  gameImage.getWidth()/2 + gameImage.getHeight()/2,
                                   gameImage.getHeight());
                screen.drawBitmap(gameImage, gameImageSrc,gameImageDst, null);
            }

            getHolder().unlockCanvasAndPost(screen);
        }
    }
}
