package com.example.klay_fx.retrogame2018s2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * @author Xuan Feng, Yutong Wang, Xinli Xu
 */
public class GameActivity extends AppCompatActivity implements GameOver {

    static TextView counter;
    RelativeLayout rl;
    static int dayOrNight = 1;
    GameView gameView;
    Game game; //?
    private boolean isPause = false;

    ImageView scoreboard;
    ImageButton restart;
    ImageButton backToMenu;
    ImageButton pauseButton;
    ImageView gameover;
    static ImageView usage;
    TextView bestScore;
    TextView currentScore;
    ImageButton add;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        MainActivity.load();

        gameover = findViewById(R.id.imageView5);
        gameover.setVisibility(View.INVISIBLE);

        scoreboard = findViewById(R.id.imageView2);
        scoreboard.setVisibility(View.INVISIBLE);

        restart = findViewById(R.id.restart);
        restart.setVisibility(View.INVISIBLE);

        backToMenu = findViewById(R.id.backtomenu);
        backToMenu.setVisibility(View.INVISIBLE);

        currentScore = findViewById(R.id.textView4);
        currentScore.setVisibility(View.INVISIBLE);

        bestScore = findViewById(R.id.textView5);
        bestScore.setVisibility(View.INVISIBLE);

        pauseButton = findViewById(R.id.pause);

        usage = findViewById(R.id.usage);

        add = findViewById(R.id.add);
        add.setVisibility(View.INVISIBLE);


        rl = findViewById(R.id.rootRL);
        if (dayOrNight == 1) {
            rl.setBackgroundResource(R.drawable.background1);
            dayOrNight --;
        } else {
            rl.setBackgroundResource(R.drawable.background2);
            dayOrNight ++;
        }

//        add a textview to show a counter
        counter = findViewById(R.id.counter);
        counter.setTextColor(Color.WHITE);
        Game.counter = 0;
        gameView = findViewById(R.id.gameview);
        game = gameView.game; //?


        gameView.registerGameOver(this);
    }


    @Override
    public void gameOver() {
        System.out.println("hi");
        scoreboard.setVisibility(View.VISIBLE);
        restart.setVisibility(View.VISIBLE);
        backToMenu.setVisibility(View.VISIBLE);
        gameover.setVisibility(View.VISIBLE);
        bestScore.setText((Game.counter > MainActivity.score[0]) ? ""+Game.counter : ""+MainActivity.score[0]);
        bestScore.setVisibility(View.VISIBLE);
        currentScore.setText(String.valueOf(Game.counter));
        currentScore.setVisibility(View.VISIBLE);
        counter.setVisibility(View.INVISIBLE);
        add.setVisibility(View.VISIBLE);


    }

    //TODO: TEST
    public void writeToFile(View view) {
        try {
            FileOutputStream fileout;
            try {
                fileout = openFileOutput("sayhiiii.txt", MODE_APPEND);
            } catch (FileNotFoundException e) {
                fileout = openFileOutput("sayhiii.txt", MODE_PRIVATE);
            }
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("" + Game.counter +"\n");
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "Score saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



//    // Read text from file
//    public void readBtn(View v) {
//
//    }

    /**
     * Handle the pause button click to pause and continue the game.
     * @param view
     */
    public void pauseGame(View view) {

        if(!isPause) {
            isPause = true;
            pauseButton.setImageResource(R.drawable.resume);
            gameView.repaintHandler.removeCallbacks(gameView);
        } else {
            isPause = false;
            pauseButton.setImageResource(R.drawable.pauce);
            gameView.repaintHandler.postDelayed(gameView, 100);
        }
    }

    /**
     * 1) Handle the restart button click to restart the game
     * 2) Handle the backToMenu button click to back to the previous activity
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.restart:
                // TODO: RESTART FUNCTION
                Intent intent_restart = new Intent(this,GameActivity.class);
                finish();
                startActivity(intent_restart);
                break;
            case R.id.backtomenu:
                setResult(RESULT_OK);
                finish();
                break;
        }
    }

}
