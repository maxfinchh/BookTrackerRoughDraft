package edu.ucdenver.salimlakhani.phonebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookEntryAdapter extends RecyclerView.Adapter<BookEntryAdapter.ListItemHolder> {

    private MainActivity mainActivity;
    private ArrayList<BookEntry> list;

    public BookEntryAdapter(MainActivity mainActivity, ArrayList<BookEntry> list) {
        this.mainActivity = mainActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);

        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        BookEntry bookEntry = list.get(position);
        holder.textViewBookTitle.setText(bookEntry.getBookTitle());
        holder.textViewAuthor.setText(bookEntry.getAuthor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder {

        private TextView textViewBookTitle;
        private TextView textViewAuthor;
        private Button buttonDelete;
        private Button buttonEdit; // Add the edit button

        public ListItemHolder(View itemView) {
            super(itemView);
            textViewBookTitle = itemView.findViewById(R.id.textViewBookTitle);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonEdit = itemView.findViewById(R.id.buttonEdit); // Initialize the edit button

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                        mainActivity.deleteBookEntry(getAdapterPosition());
                    }
                }
            });

            buttonEdit.setOnClickListener(new View.OnClickListener() { // Set the click listener for edit
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                        mainActivity.editBookEntry(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
}
