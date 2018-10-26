package me.mahakagg.twoactivitiesV2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName() ;
    public static final String EXTRA_MESSAGE = "me.mahakagg.twoactivitiesV2.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private EditText mMessageEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        get the views from their IDs
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

        if (savedInstanceState != null){
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible){
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_visible"));
            }
        }

        Log.d(LOG_TAG, "-------------");
        Log.d(LOG_TAG, "onCreate");
    }

    public void launchSecondActivity(View view) {
//        add message to debug logs
        Log.d(LOG_TAG, "Button Clicked!");
//        create intent, get string from EditText, put it in intent extras and send to SecondActivity
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
//       receive result back from SecondActivity
        startActivityForResult(intent, TEXT_REQUEST);
    }

//    method callback when SecondActivity returns result
//    request code is the one used in startActivityForResult(); resultCode is the code which is received (can be RESULT_OK or RESULT_CANCELED; data is the intent which carries the data
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST){
            if (resultCode == RESULT_OK){
//                get the reply message from SecondActivity, put the information in TextView and make them visible
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
            }
        }
    }

    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        check if the reply header text is visible
        if(mReplyHeadTextView.getVisibility() == View.VISIBLE){
//           put this in the saved instance bundle with key - reply_visible
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mReplyTextView.getText().toString());
        }
    }
}
