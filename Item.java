package com.example.klay_fx.retrogame2018s2;

import android.graphics.Paint;
import android.graphics.Canvas;

/**
 * Item - a single drawable item located at a particular position in the game area
 */



public abstract class Item {
    Pos pos;

    public abstract void draw(Canvas c, Paint p);
}
