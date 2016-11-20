package com.example.david.eande;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    public Double timeRemaining = 0.0;
    final Integer time = 24;
    public Integer work = 8;
    public Integer sleep = 7;
    public Integer activities = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activities = time - (work + sleep);

        RadioButton rbdefault = (RadioButton)findViewById(R.id.rb_default);
        rbdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activities = time - (work + sleep);
                Toast toast = Toast.makeText(getApplicationContext(), ("default" + activities), LENGTH_SHORT);
                toast.show();
            }
        });

        RadioButton rbswing = (RadioButton)findViewById(R.id.rb_swing);
        rbswing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activities = time - (work + 5);
                Toast toast = Toast.makeText(getApplicationContext(), ("swing" + activities), LENGTH_SHORT);
                toast.show();
            }
        });

        RadioButton rbgraveyard = (RadioButton)findViewById(R.id.rb_graveyard);
        rbgraveyard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activities = time - (work + 2);
                Toast toast = Toast.makeText(getApplicationContext(), ("graveyard" + activities), LENGTH_SHORT);
                toast.show();
            }
        });
        
    }


}
