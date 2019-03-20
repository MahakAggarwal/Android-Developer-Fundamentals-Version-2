package me.mahakagg.whowroteitloadercc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public BookLoader(@NonNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
