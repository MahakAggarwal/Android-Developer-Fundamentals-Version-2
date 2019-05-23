package me.mahakagg.roomwordssample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
* adapter class for recyclerView
* */

/*
* Adapter is used to connect data to view items
* */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords;

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    // inflate a view item and return the viewHolder containing it
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    // set content of view item at a certain position
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }
        else{
            holder.wordItemView.setText("No Word");
        }
    }

    //
    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // return the number of items in the RecyclerView
    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        }
        else {
            return 0;
        }
    }

    /*
    * ViewHolder is in charge of displaying one item using the separate recyclerView layout
    * */
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
