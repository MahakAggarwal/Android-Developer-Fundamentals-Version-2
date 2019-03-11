package me.mahakagg.simpleasynctaskcc;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

// first void because no input required; second void because progress is not published; string for result type
public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

   // The weak reference prevents the memory leak by allowing the object held by that reference to be garbage collected if necessary.
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mResultTextView;

    SimpleAsyncTask (TextView tv, TextView result){
        mTextView = new WeakReference<>(tv);
        mResultTextView = new WeakReference<>(result);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int number = random.nextInt(11);
        int milli = number * 200;
        publishProgress(milli);
        try {
            Thread.sleep(milli);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Awake after sleeping for " + milli + " milliseconds";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mResultTextView.get().setText("Current sleep time: " + values[0] + " ms");
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
