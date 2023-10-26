package edu.ucdenver.salimlakhani.phonebook;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ListItemHolder> {

    private MainActivity mainActivity;
    private ArrayList<Contact> list;

    public ContactAdapter (MainActivity mainActivity, ArrayList<Contact> list) {
        this.mainActivity = mainActivity;
        this.list = list;

    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.list_layout, parent, false);

        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {

        Contact contact = list.get (position);

        holder.textViewName.setText(contact.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewName;

        public ListItemHolder (View itemView) {
            super (itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewName.setClickable(true);
            textViewName.setOnClickListener(this);
        }

        public void onClick (View view){


        }
    }


}
