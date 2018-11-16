package com.example.klay_fx.retrogame2018s2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Xinli Xu
 */

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        TextView tv1 = findViewById(R.id.textView);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);
        tv1.setText(""+MainActivity.score[0]);
        tv2.setText(""+MainActivity.score[1]);
        tv3.setText(""+MainActivity.score[2]);
    }
}
