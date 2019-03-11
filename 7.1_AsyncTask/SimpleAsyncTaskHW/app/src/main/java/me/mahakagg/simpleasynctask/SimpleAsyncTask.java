package me.mahakagg.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

// first void because no input required; second void because progress is not published; string for result type
public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

   // The weak reference prevents the memory leak by allowing the object held by that reference to be garbage collected if necessary.
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
    private static final int CHUNK_SIZE = 5;

    SimpleAsyncTask (TextView tv, ProgressBar bar){
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(bar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int number = random.nextInt(11);
        int milli = number * 400;
        int chunkSize = milli / CHUNK_SIZE;
        for (int i = 0; i < CHUNK_SIZE; i++){
            try {
                Thread.sleep(chunkSize);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            publishProgress(((i + 1) * 100) / CHUNK_SIZE);
        }

        return "Awake after sleeping for " + milli + " milliseconds";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
