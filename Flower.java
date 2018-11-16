package com.example.klay_fx.retrogame2018s2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * The flower (bird eater)
 * @author Yutong Wang
 */

public class Flower {

    private static final float FLOWER_STEP = 0.015f;
    private static final float FLOWER_WIDTH = 0.1f;
    public static final float FLOWER_HEIGHT = 0.12f;

    private Bitmap f1;
    private Bitmap f2;

    // which flower img should use
    int flowerFrame;
    float x;
    float y;

    // move up or move down
    int direction;

    Flower(Pos pos) {
        f1 = SpriteSheet.flower1;
        f2 = SpriteSheet.flower2;
        x = pos.x;
        y = pos.y + 0.2f;
        direction = 0;
        flowerFrame = 0;
    }

    public void draw(Canvas c, Paint p) {
        int w = c.getWidth();
        int h = c.getHeight();

        if(flowerFrame == 0) {
            f1 = Bitmap.createScaledBitmap(f1, (int) (FLOWER_WIDTH * w), (int) (FLOWER_HEIGHT * h), true);
            c.drawBitmap(f1, (x - 0.05f) * w, y * h, null);
            flowerFrame = 1;
        } else {
            f2 = Bitmap.createScaledBitmap(f2, (int) (FLOWER_WIDTH * w), (int) (FLOWER_HEIGHT * h), true);
            c.drawBitmap(f2, (x - 0.05f) * w, y * h, null);
            flowerFrame = 0;
        }


    }

    // moving flower
    // x: moving with fpillar
    // y: moving up and down
    public void step(float pillarX, float pillarY) {
        x = pillarX;
        // if move up to half of the gap, then move down
        if(direction == 0) {
            y -= FLOWER_STEP;
        } else {
            // move up
            y += FLOWER_STEP;
        }

        if(y >= pillarY + 0.15f) direction = 0;
        if(y <= pillarY + 0.02f) direction = 1;

    }
}
