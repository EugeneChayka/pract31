package com.example.pract31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;


import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;



public class MainActivity extends AppCompatActivity  {
    private GestureDetectorCompat lSwipeDetector;
    private static final int SWIPE_DISTANCE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lSwipeDetector = new GestureDetectorCompat(this, new MyGestureListener());


        findViewById(R.id.scrollView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return lSwipeDetector.onTouchEvent(event);
            }
        });
        findViewById(R.id.btnTouch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TouchActivity.class);
                startActivity(intent);
            }
        });
    }
    public  void onSwipeUp()
    {
        Intent intent = new Intent(MainActivity.this, UpActivity.class);
        startActivity(intent);
    }
    public void onSwipeDown()
    {
        Intent intent = new Intent(MainActivity.this, DownActivity.class);
        startActivity(intent);
    }
    public void onSwipeLeft() {
        Intent intent = new Intent(MainActivity.this, RightActivity.class);
        startActivity(intent);
    }

    public void onSwipeRight() {
        Intent intent = new Intent(MainActivity.this, LeftActivity.class);
        startActivity(intent);
    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX>0)
                    onSwipeRight();
                if( distanceX<0)
                    onSwipeLeft();
                return true;
            } else if (Math.abs(distanceY) > Math.abs(distanceX) && Math.abs(distanceY) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceY > 0)
                        onSwipeUp();
                    if (distanceY < 0)
                        onSwipeDown();
                    return true;
            }
            return false;
        }
    }
}