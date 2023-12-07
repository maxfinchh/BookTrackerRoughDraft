package edu.ucdenver.salimlakhani.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import edu.ucdenver.salimlakhani.phonebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ArrayList<BookEntry> list;
    private ActivityMainBinding binding;
    private BookEntryAdapter bookEntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        list = new ArrayList<>();

        bookEntryAdapter = new BookEntryAdapter(this, list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.content.recyclerView.setLayoutManager(layoutManager);
        binding.content.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.content.recyclerView.setAdapter(bookEntryAdapter);
    }

    public void addBookEntry(BookEntry bookEntry) {
        list.add(bookEntry);
        bookEntryAdapter.notifyDataSetChanged();
    }

    // Method to delete a book entry from the list
    public void deleteBookEntry(int position) {
        if(position >= 0 && position < list.size()) {
            list.remove(position);
            bookEntryAdapter.notifyItemRemoved(position);
            bookEntryAdapter.notifyItemRangeChanged(position, list.size());
        }
    }

    public void editBookEntry(int position) {
        BookEntry bookEntry = list.get(position);
        AddBookDialog editDialog = new AddBookDialog();

        // Pass the book entry data to the dialog, possibly through a bundle
        Bundle bundle = new Bundle();
        bundle.putInt("position", position); // Pass the position to know where to update
        bundle.putString("bookTitle", bookEntry.getBookTitle());
        bundle.putString("author", bookEntry.getAuthor());
        bundle.putString("readStatus", bookEntry.getReadStatus());
        editDialog.setArguments(bundle);

        // Show the dialog
        editDialog.show(getSupportFragmentManager(), "editBook");
    }

    public void updateBookEntry(int position, BookEntry bookEntry) {
        // Update the book entry in the list and notify the adapter
        if(position >= 0 && position < list.size()) {
            list.set(position, bookEntry);
            bookEntryAdapter.notifyItemChanged(position);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            AddBookDialog addBookDialog = new AddBookDialog();
            addBookDialog.show(getSupportFragmentManager(), "");
        }

        return super.onOptionsItemSelected(item);
    }
}
