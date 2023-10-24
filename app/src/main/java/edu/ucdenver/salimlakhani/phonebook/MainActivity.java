package edu.ucdenver.salimlakhani.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import edu.ucdenver.salimlakhani.phonebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> list;
    private ActivityMainBinding binding;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<Contact>();

        contactAdapter = new ContactAdapter(this, list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.content.recyclerView.setLayoutManager(layoutManager);
        //binding.content.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.content.recyclerView.setAdapter(contactAdapter);
    }

    public void addContact(View view) {
       // Toast.makeText(this, "You clicked on Add Contact Button", Toast.LENGTH_LONG).show();
        AddContactDialog addContactDialog = new AddContactDialog();
        addContactDialog.show(getSupportFragmentManager(), "");
    }


    public void addContact (Contact contact) {
        list.add(contact);
        //Log.i("info", "Number of Contact" + list.size());
        contactAdapter.notifyDataSetChanged();
    }
}