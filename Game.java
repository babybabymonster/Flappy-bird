package com.example.klay_fx.retrogame2018s2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.prefs.Preferences;

/**
 * Game - this is the model of the main game.
 *
 * @author Xuan Feng, Yutong Wang
 */

public class Game {
    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;

    //starting position of bird
    public static final float BIRD_X = 0.4f; //?
    public static final float BIRD_Y = 0.5f;
    public static final float GROUND_BOUND = 5.6f / 7.0f;
    //public static final float BIRD_STEP = 0.02f;

    // All kinds of game states
    protected static final int GAME_READY = 0;
    protected static final int GAME_PLAYING = 1;
    protected static final int GAME_DYING = 2;
    protected static final int GAME_DEAD = 3;

    // Current game state
    protected static int game_state;

    // Score counter
    public static int counter = 0;

    //Preferences
//    static Preferences prefs;
//    public static ArrayList<Integer> scoreList = new ArrayList<>();


    //Entities in the game
    private Bird bird;
    private Pillars pillars;
    private Pos birdStart = new Pos(BIRD_X,BIRD_Y);
    private Grounds grounds;

    private boolean birdHit;

    public Game(){
        bird = new Bird(birdStart);
        pillars = new Pillars(); //what input ?
        grounds = new Grounds();
        birdHit = false;
        game_state = GAME_READY;
//        prefs = Preferences.userNodeForPackage(Game.class);
    }

    /**
     * Draw all the entities.
     */
    public void draw(Canvas c, Paint p) {
        pillars.draw(c, p); // param?
        bird.draw(c, p); //what parameter?
        grounds.draw(c,p);
    }

    /**
     * @function Check the game state and call different state functions below.
     */
    public void step() {

        // ground is always there
        if (grounds.size() == 0) {
            grounds.add(new Ground(new Pos(0.5f, 1 - Ground.GROUNDHEIGHT)));
            grounds.add(new Ground(new Pos(1.5f, 1 - Ground.GROUNDHEIGHT)));
        } else if (grounds.size() == 1) {
            grounds.add(new Ground(new Pos(1.45f, 1 - Ground.GROUNDHEIGHT)));
        }
        grounds.step();

        switch (game_state) {

            case GAME_READY:
                ready();
                break;

            case GAME_PLAYING:
                playing();
                break;

            case GAME_DYING:
                dying();
                break;

            case GAME_DEAD:
                dead();
                break;

        }
    }

    /**
     * Different update methods according to different game states.
     */
    private void ready() {

    }

    private void playing() {
        game_state = GAME_PLAYING;

        if (bird.hitBy(pillars)) {
            game_state = GAME_DYING;
            birdHit = true;

        } //multi lives?
        if (pillars.size() == 0) {
            pillars.getPillar();
        } else if (pillars.size() == 1 && pillars.get(0).pos.x < bird.pos.x) {
            pillars.getPillar();
            counter++;
        }

        bird.step();
        pillars.step();
    }

    private void dying() {
        game_state = GAME_DYING;

        //bird dropping
        float dyingBirdY = bird.step();
        if(dyingBirdY >= GROUND_BOUND) {
            dead();
        }
    }

    public void dead() {
        // bird finally dead
        game_state = GAME_DEAD;
    }


    /**
     * Return true if bird is dead.
     */
    public boolean isBirdHit() { //multi lives?
        return birdHit;
    }

    // the game is endless?
    public boolean hasWon() {
        return true;
    }

    /**
     * This method is called when user 'tap' the screen - switch the game state
     * from 'READY' to 'PLAYING'.
     */
    public void birdFly() {
        if(game_state == GAME_READY || game_state == GAME_PLAYING) {
            game_state = GAME_PLAYING;
            GameActivity.usage.setVisibility(View.INVISIBLE);
            Bird.v = -0.038f;
        }
    }
}
