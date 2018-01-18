package com.fraserbrooks.game.state;

/**
 * Created by Frase on 20/12/2017.
 */


import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.fraserbrooks.framework.util.Painter;
import com.fraserbrooks.game.model.Block;
import com.fraserbrooks.game.model.Cloud;
import com.fraserbrooks.game.model.Player;
import com.fraserbrooks.dodo_go.Assets;
import com.fraserbrooks.dodo_go.GameMainActivity;

public class PlayState extends State {

    private Player player;
    private ArrayList<Block> blocks;
    private Cloud cloud,cloud2;
    private int playerScore = 0;
    private static final int BLOCK_HEIGHT = 150;
    private static final int BLOCK_WIDTH = 60;
    private int blockSpeed = -450;
    private static final int PLAYER_WIDTH = 280;
    private static final int PLAYER_HEIGHT = 250;

    private float recentTouchY;

    @Override
    public void init() {
        player = new Player(GameMainActivity.GAME_WIDTH / 2 - PLAYER_WIDTH , GameMainActivity.GAME_HEIGHT - 45 - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT );
        blocks = new ArrayList<Block>();
        cloud = new Cloud(100, 100);
        cloud2 = new Cloud(500, 50);
        for(int i = 0; i < 5; i++){
            Block b = new Block(i * 500, GameMainActivity.GAME_HEIGHT - 95, BLOCK_WIDTH, BLOCK_HEIGHT , this);
            blocks.add(b);
        }
    }

    @Override
    public void update(float delta) {
        if (!player.isAlive()){
            setCurrentState(new GameOverState(playerScore));
        }

        cloud.update(delta);
        cloud2.update(delta);
        Assets.runAnim.update(delta);
        player.update(delta);
        updateBlocks(delta);
    }

    public void increaseScore(){
        playerScore += 1;
    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.rgb(152,193,218));
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        renderPlayer(g);
        renderBlocks(g);
        renderSun(g);
        renderClouds(g);
        g.drawImage(Assets.grass, 0, 810, 2000, Assets.grass.getHeight());
        renderScore(g);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN){
            recentTouchY = scaledY;
        }else if (e.getAction() == MotionEvent.ACTION_UP){
            if (scaledY - recentTouchY < -50){
                player.jump();
            } else if (scaledY - recentTouchY > 50){
                player.duck();
            }
        }
        return true;
    }

    private void updateBlocks(float delta){
        for (Block b : blocks){
            b.update(delta, blockSpeed);
            if(b.isVisible()){
                if(player.isDucked() && Rect.intersects(b.getRect(), player.getDuckRect())){
                    b.onCollide(player);
                } else if (!player.isDucked() && Rect.intersects(b.getRect(), player.getRect())){
                    b.onCollide(player);
                }
            }
        }
    }


    private void renderScore(Painter g){
        g.setFont(Typeface.SANS_SERIF, 450);
        g.setColor(Color.rgb(255,165, 0));
        g.drawString("" + playerScore ,  GameMainActivity.GAME_WIDTH / 2 - 200, GameMainActivity.GAME_HEIGHT / 2 - 90);
    }

    private void renderPlayer(Painter g){
        if (player.isGrounded()){
            if (player.isDucked()){
                g.drawImage(Assets.duck ,(int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
            } else {
                Assets.runAnim.render(g,  (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
            }
        } else {
            Assets.jumpAnim.render(g,  (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
        }
    }

    private void renderBlocks(Painter g){
        for (Block b : blocks){
            if (b.isVisible()){
                g.drawImage(Assets.block, (int) b.getX(), (int) b.getY(), BLOCK_WIDTH, BLOCK_HEIGHT);
            }
        }
    }

    private void renderSun(Painter g){
        g.setColor(Color.rgb(255,165, 0));
        g.fillOval(1530, -170, 340, 340);
        g.setColor(Color.YELLOW);
        g.fillOval(1550, -150, 300, 300);
    }

    private void renderClouds(Painter g){
        g.drawImage(Assets.cloud1, (int) cloud.getX(), (int) cloud.getY(), 100, 60);
        g.drawImage(Assets.cloud2, (int) cloud2.getX(), (int) cloud2.getY(), 100, 60);
    }

}
