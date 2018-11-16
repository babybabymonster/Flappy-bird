package com.example.klay_fx.retrogame2018s2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.InputStream;
import java.util.Random;


/**
 * The pillar can either be a normal pillar or a pillar with flower(bird eater).
 * The show up of these two pillars is random.
 * @author Xuan Feng, Yutong Wang
 */
public class Pillar extends Item {
    public static final float PILLARWIDTH = (1.0f / 8.0f);
    public static final float PILLARHEIGHT = 1.0f;
    public static final float PILLARSTEP = 0.03f;

    Bitmap b1 = SpriteSheet.pillar1;
    Bitmap b2 = SpriteSheet.pillar2;

    Flower flower;

    // TODO: 17/10/18 need bitmap 
    public Pillar(Pos p) {
        pos = new Pos(p);
    }

    public Pillar(Pos p, Flower f) {
        pos = new Pos(p);
        flower = f;
    }

    public void step() {
        pos.x -= PILLARSTEP;
        if(flower != null) flower.step(pos.x, pos.y);
    }

    // draw the Pillar
    public void draw(Canvas c , Paint p) {

        if(flower != null) flower.draw(c, p);

        int w = c.getWidth();
        int h = c.getHeight();


        float xc = pos.x * w;
        float yc = pos.y * h ;
        float left = xc - PILLARWIDTH/2*w;
        float right = xc + PILLARWIDTH/2*w;


        float whitetop = yc - 0.115f * h;
        float whitebottom = yc + 0.115f * h;




        b1 = Bitmap.createScaledBitmap(b1, (int) (PILLARWIDTH * w), (int) (PILLARHEIGHT * h), true);
        c.drawBitmap(b1,left,whitetop - PILLARHEIGHT*h,p);

        b2 = Bitmap.createScaledBitmap(b2, (int) (PILLARWIDTH * w), (int) (PILLARHEIGHT * h), true);
        c.drawBitmap(b2,left,whitebottom,p);



    }
}
