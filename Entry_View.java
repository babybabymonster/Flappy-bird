package com.example.klay_fx.retrogame2018s2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * anthor: Xinli XU
 */
public class Entry_View extends View implements Runnable{
        Canvas c =new Canvas();
        public static float birdY = 0.46f;
        public static final float BirdSTep = 0.01f;
        Handler timer;
        boolean direction = true;//downward

    public Entry_View(Context context, @Nullable AttributeSet attri) {
        super(context,attri);
        timer = new Handler();
        timer.postDelayed(this,100);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        Bitmap moving_bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        moving_bird = Bitmap.createScaledBitmap(moving_bird,180,144,true);
        canvas.drawBitmap(moving_bird,0.44f*canvas.getWidth(),birdY*canvas.getHeight(),p);
        this.invalidate();
    }

    @Override
    public void run() {
        if(birdY > 0.5) {
            direction = false;
        } else if (birdY < 0.4) {
            direction = true;
        }
        if (direction) {
            birdY += BirdSTep;
        } else {
            birdY -= BirdSTep;
        }
        this.invalidate();
        timer.postDelayed(this,100);
    }
}
