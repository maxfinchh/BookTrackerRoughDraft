package edu.ucdenver.salimlakhani.phonebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        // Make sure the list_layout.xml file has TextViews with the IDs textViewBookTitle and textViewAuthor
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


    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Update these TextViews to match the BookEntry attributes
        private TextView textViewBookTitle;
        private TextView textViewAuthor;

        public ListItemHolder(View itemView) {
            super(itemView);
            textViewBookTitle = itemView.findViewById(R.id.textViewBookTitle); // Update ID as per your layout
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor); // Update ID as per your layout
            // Set up click listeners if necessary
            itemView.setOnClickListener(this);
        }

        // Implement the onClick method if you want to handle item clicks
        @Override
        public void onClick(View view) {
            // Handle the click event if necessary
        }
    }




}
