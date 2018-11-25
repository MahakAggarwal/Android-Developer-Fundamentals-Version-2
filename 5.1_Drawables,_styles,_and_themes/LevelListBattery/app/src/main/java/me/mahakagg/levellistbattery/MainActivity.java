package me.mahakagg.levellistbattery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/*
* Answers to homework questions
*
* Q1 - Which type of Drawable do you use to create a Button with a background that stretches properly to accommodate the text or image inside the Button so that it looks correct for different screen sizes and orientations?
* A1 - NinePatchDrawable
*
* Q2 - Which type of Drawable do you use to create a Button that shows one background when it is pressed and a different background when it is hovered over?
* A2 - StateListDrawable
*
* Q3 - Suppose you want to create an app that has a white background, dark text, and a dark action bar. Which base style does your application style inherit from?
* A3 - Theme.AppCompat.Light.DarkActionBar
*
* */

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private int imageLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.battery_image);
        image.setImageLevel(imageLevel);
    }

    public void changeBatteryLevel(View view) {
        switch (view.getId()){
            case R.id.decrease_btn:
                if (imageLevel > 0){
                    imageLevel --;
                    image.setImageLevel(imageLevel);
                }
                break;
            case R.id.increase_btn:
                if (imageLevel < 6){
                    imageLevel ++;
                    image.setImageLevel(imageLevel);
                }
                break;
            default: break;
        }
    }
}
