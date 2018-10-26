package me.mahakagg.managingstatehw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
* Answers to questions from section 2.2
*
* Q1 - When you rotate the device (before you implement onSaveInstanceState()), the counter is reset to 0 but the contents of the edit text is preserved. Why?
* A1 - system destroys the activity when configuration change occurs. The onCreate, onStart and onResume methods are called all over again.
*
* Q2 -What Activity lifecycle methods are called when a device-configuration change (such as rotation) occurs?
 * A2 - When config change occurs the Android OS shuts down the activity (calling onPause(), onStop() and onDestroy()) and starts the activity all over again (onCreate(), onStart() and onResume())
*
* Q3 - When in the Activity lifecycle is onSaveInstanceState() called?
* A3 - This method is called before onStop()
*
* Q4 - Which is the correct method signature for onSaveInstanceState()?
* A4 - void onSaveInstanceState(Bundle outState) and void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
*
* Q5 - What is the difference between restoring your activity state in onCreate() versus in onRestoreInstanceState()?
* A5 - onRestoreInstanceState() can be used over onCreate() just for convenience after all the initialization or to allow subclasses to decide whether to use your default implementation (answer from Stackoverflow post : https://stackoverflow.com/questions/36408776/using-oncreate-vs-onrestoreinstancestate)
*
* Q6 - If you quit and restart your app, what happens to the Activity state?
* A6 - Activity gets destroyed when quitting and is created again when restarted.
*
* */

public class MainActivity extends AppCompatActivity {
    private TextView countTextView;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTextView = findViewById(R.id.count_textView);
        if (savedInstanceState != null){
            String i = savedInstanceState.getString("count_value");
            countTextView.setText(i);
            count = Integer.parseInt(i);
        }
    }

    public void increment(View view) {
        count ++;
        countTextView.setText(String.valueOf(count));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("count_value", countTextView.getText().toString());
    }


}
