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
