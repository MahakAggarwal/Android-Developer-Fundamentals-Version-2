package me.mahakagg.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.LinkedList;

// adapter to connect linked list items to the Recycler view
// WordListAdapter extends generic adapter which uses a custom made ViewHolder
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    // a ViewHolder class is required to describe a view item and its position within the recycler view
    // prepare data in a ViewHolder
    // viewHolder initializes items for the RecyclerView (efficiency)
    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView wordItemView;
        final WordListAdapter mAdapter;


        // in this constructor viewHolder has to inflate layout, set adapter and set onClick listener if applicable
        WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        // onclick method implemented here
        // get position of item clicked, use position to get the item from linked list, make changes and
        // notify the adapter that there has been a change so it can update recyclerView
        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            String element = mWordList.get(mPosition);
            mWordList.set(mPosition, "Clicked! " + element);
            mAdapter.notifyDataSetChanged();
        }
    }

    // get inflater from current context and data in this constructor
    // constructor needed to initialize word list from data; also need to inflate layout from XML file
    WordListAdapter(Context context, LinkedList<String> wordList){
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    /*
    This method inflates a View item and returns a new ViewHolder that contains it.
    It is called when the RecyclerView needs a new ViewHolder to represent an item
    */
    // this class also initializes the view holder class
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate view wordList_item (made for 1 item in the recycler view)
        View mItemView = mInflater.inflate(R.layout.worldlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    /*
    onBindViewHolder() sets the contents of a View item at a given position in the RecyclerView.
    This is called by the RecyclerView, for example, when a new View item scrolls onto the screen.
    This method binds the data with the viewHolder
    */
    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    // return the total number of items held in the data set by the adapter
    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
