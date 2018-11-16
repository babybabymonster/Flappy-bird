package com.example.klay_fx.retrogame2018s2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * This class load all the images needed.
 * @author Yutong Wang
 */

public class SpriteSheet extends View {

    protected static Bitmap bird1;
    protected static Bitmap bird2;
    protected static Bitmap bird3;
    protected static Bitmap dead_bird;
    protected static Bitmap pillar1;
    protected static Bitmap pillar2;
    protected static Bitmap flower1;
    protected static Bitmap flower2;
    protected static Bitmap ground;
    protected static Bitmap usage;
    protected static Bitmap scoreboard;

    protected Bitmap logo;//?

    public SpriteSheet(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bird1 = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        bird2 = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);
        bird3 = BitmapFactory.decodeResource(getResources(), R.drawable.bird3);
        dead_bird = BitmapFactory.decodeResource(getResources(), R.drawable.dead_bird);
        pillar1 = BitmapFactory.decodeResource(getResources(), R.drawable.pipe);
        pillar2 = BitmapFactory.decodeResource(getResources(), R.drawable.pipereversed);
        flower1 = BitmapFactory.decodeResource(getResources(), R.drawable.f1);
        flower2 = BitmapFactory.decodeResource(getResources(), R.drawable.f2);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
//        logo = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        usage = BitmapFactory.decodeResource(getResources(), R.drawable.usage);
        scoreboard = BitmapFactory.decodeResource(getResources(), R.drawable.scoreboard);
    }


}
