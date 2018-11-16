package com.example.klay_fx.retrogame2018s2;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Xuan Feng
 */
public class Grounds extends ArrayList<Ground> {
    public static final float GROUNDSTEP = 0.03f;


    public void step() {
        for (Ground b : this) b.pos.x -= GROUNDSTEP;
        Iterator<Ground> bi = this.iterator();
        while (bi.hasNext()) {
            Ground b = bi.next();
            if (b.pos.x < (-1.0f / 2.0f)) {
                bi.remove();
            }
        }
    }


    public void draw(Canvas canvas, Paint paint) {
        for (Ground b : this) b.draw(canvas, paint);
    }
}
