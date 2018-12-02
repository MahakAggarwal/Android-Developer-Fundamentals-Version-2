package me.mahakagg.transitionsanimations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void ToastMessage(View view) {
        Toast.makeText(this , "Go to previous activity for animations", Toast.LENGTH_LONG).show();
    }
}
