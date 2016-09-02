package com.umdasl.robot.robotcontroller;



import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    Button recordButton;
    boolean recording;
    RelativeLayout leftMotorContainer;
    ImageView leftMotor;
    RelativeLayout rightMotorContainer;
    ImageView rightMotor;
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
                    stopRecording();
                }
            }
        });
        leftMotorContainer = (RelativeLayout)findViewById(R.id.leftMotorContainer);
        leftMotor = (ImageView)findViewById(R.id.leftMotor) ;
        leftMotorContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("robot",Float.toString(event.getY()));
                leftMotor.setY(event.getY()-leftMotor.getHeight()/2);
                return false;
            }
        });
        rightMotorContainer = (RelativeLayout)findViewById(R.id.rightMotorContainer);
        rightMotor = (ImageView)findViewById(R.id.rightMotor) ;
        rightMotorContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("robot",Float.toString(event.getY()));
                rightMotor.setY(event.getY()-rightMotor.getHeight()/2);
                return false;
            }
        });
    }
    void stopRecording(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to stop taking a vedio?")
                .setPositiveButton("Yes,stop it!", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recording = false;
                        recordButton.setText("Start Recording");
                        recordButton.setBackgroundColor(getResources().getColor(R.color.Green));
                    }

                })
                .setNegativeButton("No,keep taking it!", null)
                .show();
    }
}
