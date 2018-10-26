package me.mahakagg.helloToastHW1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

/*
* Answers to questions from section 2.1
*
* Q1 - What menu command do you use to add a new activity to your app?
* A1 - navigate to folder where the MainActivity Java class resides. Right click and New > Activity
*
* Q2 - What files are added when you add a new activity called HelloActivity to your app? What changes are made to existing files?
* A2 - Java class, layout file(s), and changes in the manifest file
*
* Q3 - Which constructor method do you use to create a new explicit intent?
* A3 - new Intent(Context context, Class<?> class)
*
* Q4 - How do you add the current value of the count to the intent?
* A4 - As an intent extra
*
* Q5 - How do you update the count in HelloActivity to display the current count?
* A5 -Get the intent the activity was launched with. Get the current count value out of the intent. Update the text view for the count. (all of the above)
* 
* */

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    public static final String EXTRA_COUNT = "me.mahakagg.helloToastHW1.COUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.show_count);
    }

    public void countUp(View view) {
        mCount ++;
        if (mShowCount != null){
            mShowCount.setText(String.format(Locale.ENGLISH, "%d", mCount));
        }
    }

    public void sayHello(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(EXTRA_COUNT, mCount);
        startActivity(intent);
    }
}
