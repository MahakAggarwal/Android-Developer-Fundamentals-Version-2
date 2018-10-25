package me.mahakagg.scrollingtext2HW;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
* Answers to questions from section 1.3
*
* Q1 - How many Views can a ScrollView contain?
* A1 - One View or one ViewGroup
*
* Q2 - Which XML attribute do you use in a LinearLayout to show views side-by-side?
* A2 - android:orientation="horizontal"
*
* Q3 - Which XML attribute do you use to define the width of the LinearLayout inside the scrolling view?
* A3 - android:layout_width="200dp"
*
*
* Answers to questions from section 1.4
*
* Q1 - In Android Studio, what is menu command to open the list of sample apps?
* A1 - File > New > Import Sample
*
* Q2 - What did you look up, and what are the URLs to the documentation you found?
* A2 - Understanding android activity lifecycle (https://developer.android.com/guide/components/activities/activity-lifecycle)
*
* Q3 - What are 2 differences between the kind of information you find in the Android Developer documentation and on Stackoverflow? When would you use the Android Developer documentation? When would you use Stackoverflow?
* A3 - Android developer documentation used to look up constants, methods, and examples. Stackoverflow used to find answers to common problems
*
* Q4 - What is the URL to the Android Studio Playlist or video that you watched? What did you learn?
* A4 - Android Developer Fundamentals (https://www.youtube.com/playlist?list=PLlyCyjh2pUe9wv-hU4my-Nen_SvXIzxGB)
* */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
