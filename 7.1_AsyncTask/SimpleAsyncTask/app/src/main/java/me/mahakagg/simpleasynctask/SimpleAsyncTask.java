package me.mahakagg.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

// first void because no input required; second void because progress is not published; string for result type
public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

   // The weak reference prevents the memory leak by allowing the object held by that reference to be garbage collected if necessary.
    private WeakReference<TextView> mTextView;

    SimpleAsyncTask (TextView tv){
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int number = random.nextInt(11);
        int milli = number * 200;
        try {
            Thread.sleep(milli);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Awake after sleeping for " + milli + " milliseconds";
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
