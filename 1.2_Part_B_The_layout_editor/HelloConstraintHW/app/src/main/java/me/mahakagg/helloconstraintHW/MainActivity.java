package me.mahakagg.helloconstraintHW;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/*
* Answers to questions from Homework section 1.2A, B
*
* Q1 - What are the layout constraint attributes on the Zero button to position it vertically equal distance between the other two buttons?
* A1 - app:layout_constraintBottom_toTopOf="@+id/button_count" and app:layout_constraintTop_toBottomOf="@+id/button_toast"
*
* Q2 - What is the layout constraint attribute on the Zero button to position it horizontally in alignment with the other two buttons?
* A2 - app:layout_constraintStart_toEndOf="@+id/button_count" and app:layout_constraintEnd_toStartOf="@id/button_toast"
*
* Q3 - Which of the following operations can you perform to include the Zero button in the xlarge (tablet) and land (landscape) layouts that have already been created?
* A3 - Either operation works
*
* Q4 - What is the correct signature for a method used as the value of the android:onClick XML attribute?
* A4 - public void callMethod(View view)
*
* Q5 - The click handler for the Count button starts with the following method signature:
        public void countUp(View view)
        Which of the following techniques is more efficient to use within this handler to change the button's background color?
* A5 - Use the view parameter that is passed to the click handler with setBackgroundColor().
* */

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    private Button mResetButton;
    private Button mCountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.show_count);
        mResetButton = findViewById(R.id.button_zero);
        mCountButton = findViewById(R.id.button_count);
    }

    public void showToast(View view) {
//        code for button toast goes here
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG);
        toast.show();
    }

    public void countUp(View view) {
//        code for button count goes here
        mCount ++;
        if (mShowCount != null){
            mShowCount.setText(String.format(Locale.ENGLISH, "%d", mCount));
        }
        mResetButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mResetButton.setClickable(true);

        switch (mCount % 2){
//            if mCount mod 2 is 0, number is even otherwise odd
            case 0: view.setBackgroundColor(Color.MAGENTA);
                break;
            case 1: view.setBackgroundColor(Color.GREEN);
                break;
            default: break;
        }
    }

    public void resetZero(View view) {
        mCount = 0;
        mShowCount.setText(String.valueOf(mCount));

        view.setClickable(false);
        view.setBackgroundColor(Color.GRAY);
        mCountButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }
}
