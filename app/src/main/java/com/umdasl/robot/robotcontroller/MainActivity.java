package com.umdasl.robot.robotcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button recordButton;
    boolean recording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code goes
        recording = false;
        recordButton = (Button)findViewById(R.id.button);
        recordButton.setText("Start Recording");
        recordButton.setBackgroundColor(getResources().getColor(R.color.Green));
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!recording){
                    recording = true;
                    recordButton.setText("Stop Recording");
                    recordButton.setBackgroundColor(getResources().getColor(R.color.Red));
                }else{
                    recording = false;
                    recordButton.setText("Start Recording");
                    recordButton.setBackgroundColor(getResources().getColor(R.color.Green));
                }
            }
        });
    }
}
