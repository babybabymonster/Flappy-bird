package com.example.klay_fx.retrogame2018s2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/***
 * @author Yutong Wang
 */

public class GameView extends View implements View.OnTouchListener, Runnable{

    public static final int DELAY_TIME = 80;

    Paint p;
    Game game;

    // Use the observer design pattern here.
    ArrayList<GameOver> observer;

    Handler repaintHandler;


    private static SpriteSheet spriteSheet;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        spriteSheet = new SpriteSheet(context, attrs);
        p = new Paint();

        this.setOnTouchListener(this);
        observer = new ArrayList<>();
        game = new Game();

        repaintHandler = new Handler();
        repaintHandler.postDelayed(this, DELAY_TIME);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        game.draw(canvas, p);
    }

    public boolean step() {
        game.step();
        this.invalidate();
        if(Game.game_state == 3) {
//            repaintHandler.removeCallbacks(this);
            showGameOver();
            return false;
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            for(int i = 0; i < 7; i++) {
                game.birdFly(); // bird moves up
                this.invalidate();
            }
        }
        return true;
    }

    private void showGameOver() {
        for(GameOver o : observer) {
            o.gameOver();
        }
    }

    // register gameover
    public void registerGameOver(GameOver gameOver) {
        observer.add(gameOver);
    }

    @Override
    public void run() {
        if(step()) {
            repaintHandler.postDelayed(this, DELAY_TIME);
        }
        GameActivity.counter.setText(Integer.toString(Game.counter));
    }
}
