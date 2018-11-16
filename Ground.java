package com.example.klay_fx.retrogame2018s2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Gound class in order to make the ground move
 * @author Xuan Feng
 */
public class Ground extends Item {
    public static final float GROUNDHEIGHT = 1.0f / 5.0f;

    Bitmap b = SpriteSheet.ground;

    public Ground(Pos p) {
        pos = new Pos(p);
    }

    // draw the Ground
    public void draw(Canvas c , Paint p) {

        int w = c.getWidth();
        int h = c.getHeight();
        if (b != null) {
            b = Bitmap.createScaledBitmap(b, (int) (1.0f * w), (int) (GROUNDHEIGHT * h), true);
            c.drawBitmap(b, pos.x * w - w / 2, pos.y * h , p);
        }
    }
}
