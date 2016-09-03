package com.umdasl.robot.robotcontroller;



import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    Button recordButton;
    boolean recording;
    WebView webView;
    RelativeLayout leftMotorContainer;
    ImageView leftMotor;
    RelativeLayout rightMotorContainer;
    ImageView rightMotor;
    float leftMotorHeight;
    float leftContainerHeight;
    float leftY;
    float leftYValue;
    float rightY;
    float rightYValue;
    float rightMotorHeight;
    float rightContainerHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code goes
        webView = (WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.google.com/");
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
                leftY = event.getY();
                leftMotorHeight = leftMotor.getHeight();
                leftContainerHeight = leftMotorContainer.getHeight();
                if(leftY -leftMotorHeight<=-leftMotorHeight/2)
                    leftY = leftMotorHeight/2;
                if(leftY + leftMotorHeight/2> leftContainerHeight)
                    leftY = leftContainerHeight-leftMotorHeight/2;
//                leftYValue=-2*(leftY-leftContainerHeight)/(leftContainerHeight-leftMotorHeight);
                leftYValue=(-leftY+538)/363;
                Log.d("robot",Float.toString(leftYValue));
                if (event.getAction() == MotionEvent.ACTION_UP)
                    leftMotor.setY(leftMotorContainer.getHeight() / 2 - leftMotor.getHeight() / 2);
                else
                    leftMotor.setY(leftY - leftMotor.getHeight() / 2);
                return true;
            }
        });
        rightMotorContainer = (RelativeLayout)findViewById(R.id.rightMotorContainer);
        rightMotor = (ImageView)findViewById(R.id.rightMotor) ;
        rightMotorContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rightY = event.getY();
                rightMotorHeight = rightMotor.getHeight();
                rightContainerHeight = rightMotorContainer.getHeight();
                if(rightY -rightMotorHeight<=-rightMotorHeight/2)
                    rightY = rightMotorHeight/2;
                if(rightY + rightMotorHeight/2> rightContainerHeight)
                    rightY = rightContainerHeight-rightMotorHeight/2;
//                rightYValue=-2*(rightY-rightContainerHeight)/(rightContainerHeight-rightMotorHeight);
                rightYValue=(-rightY+538)/363;
                Log.d("robot",Float.toString(rightYValue));
                if (event.getAction() == MotionEvent.ACTION_UP)
                    rightMotor.setY(rightMotorContainer.getHeight() / 2 - rightMotor.getHeight() / 2);
                else
                    rightMotor.setY(rightY - rightMotor.getHeight() / 2);
                return true;
            }
        });
    }
    void stopRecording(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to stop learning?")
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
