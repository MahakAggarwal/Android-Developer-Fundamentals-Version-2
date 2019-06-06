package me.mahakagg.notesroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Note> mNotes;

    NoteListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.note_recycler_adapter, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (mNotes != null){
            Note current = mNotes.get(position);
            holder.mTitleText.setText(current.getTitle());
            holder.mContentText.setText(current.getContent());
        }
        else {
            holder.mTitleText.setText(R.string.no_notes);
            holder.mContentText.setText("");
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();
        }
        else {
            return 0;
        }
    }

    void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private final TextView mTitleText;
        private final TextView mContentText;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.titleTextView);
            mContentText = itemView.findViewById(R.id.contentTextView);
        }
    }
}
