package edu.ucdenver.salimlakhani.phonebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import edu.ucdenver.salimlakhani.phonebook.databinding.DialogAddBookBinding; // Update the binding import

public class AddBookDialog extends DialogFragment {
    private DialogAddBookBinding binding; // Update the binding class

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = DialogAddBookBinding.inflate(LayoutInflater.from(getContext())); // Update the binding class

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
        binding.toolbarAddBook.inflateMenu(R.menu.menu_add_book); // Update menu reference

        binding.toolbarAddBook.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.action_exit) {
                            dismiss();
                        } else if (id == R.id.action_save) {
                            saveData();
                        }
                        else {
                            clearForm();
                        }
                        return false;
                    }
                }
        );

        binding.buttonMainMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        binding.buttonClear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearForm();
                    }
                }
        );

        binding.buttonSave.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        saveData();
                    }
                }
        );

        return builder.create();
    }

    private void clearForm() {
        binding.textInputBookTitle.setText("");
        binding.textInputAuthor.setText("");
        binding.radioButtonRead.setChecked(true); // Assuming 'Read' as default
        binding.textInputBookTitle.requestFocus();
    }

    private void saveData() {
        String bookTitle = binding.textInputBookTitle.getText().toString();
        String author = binding.textInputAuthor.getText().toString();
        String readStatus = binding.radioButtonRead.isChecked() ? "Read" : "Want to Read";

        // Replace Contact with a BookEntry class or similar
        BookEntry bookEntry = new BookEntry(bookTitle, author, readStatus);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.addBookEntry(bookEntry); // Update method to handle book entry
        dismiss();
    }
}
