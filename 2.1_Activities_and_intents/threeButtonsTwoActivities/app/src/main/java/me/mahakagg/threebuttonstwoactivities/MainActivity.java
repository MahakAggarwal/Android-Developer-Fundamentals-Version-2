package me.mahakagg.threebuttonstwoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "me.mahakagg.threebuttonstwoactivities.extra.TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showText(View view) {
        Intent intent = new Intent(this, SecondActivity.class);

//        insert some random text; got from randomtextgenerator.com
        switch (view.getId()){
            case R.id.button_one:
                intent.putExtra(EXTRA_TEXT, getString(R.string.display_one)); startActivity(intent);
                break;
            case R.id.button_two:
                intent.putExtra(EXTRA_TEXT, getString(R.string.display_two)); startActivity(intent);
                break;
            case R.id.button_three:
                intent.putExtra(EXTRA_TEXT, getString(R.string.display_three)); startActivity(intent);
                break;
            default:
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
