package edu.ucdenver.salimlakhani.phonebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import edu.ucdenver.salimlakhani.phonebook.databinding.DialogAddBookBinding;

public class AddBookDialog extends DialogFragment {
    private DialogAddBookBinding binding;
    private boolean isEditMode = false;
    private int editPosition = -1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = DialogAddBookBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

        // Check if we're in edit mode
        if (getArguments() != null) {
            isEditMode = true;
            editPosition = getArguments().getInt("position", -1);
            String bookTitle = getArguments().getString("bookTitle");
            String author = getArguments().getString("author");
            String readStatus = getArguments().getString("readStatus");

            binding.textInputBookTitle.setText(bookTitle);
            binding.textInputAuthor.setText(author);
            if ("Read".equals(readStatus)) {
                binding.radioButtonRead.setChecked(true);
            } else {
                binding.radioButtonWantToRead.setChecked(true);
            }
        }

        binding.toolbarAddBook.inflateMenu(R.menu.menu_add_book);

        binding.toolbarAddBook.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.action_exit) {
                            dismiss();
                        } else if (id == R.id.action_save) {
                            saveData();
                        } else {
                            clearForm();
                        }
                        return false;
                    }
                }
        );

        binding.buttonMainMenu.setOnClickListener(v -> dismiss());

        binding.buttonClear.setOnClickListener(v -> clearForm());

        binding.buttonSave.setOnClickListener(v -> saveData());

        return builder.create();
    }

    private void clearForm() {
        binding.textInputBookTitle.setText("");
        binding.textInputAuthor.setText("");
        binding.radioGroupReadStatus.clearCheck();
    }

    private void saveData() {
        String bookTitle = binding.textInputBookTitle.getText().toString();
        String author = binding.textInputAuthor.getText().toString();
        String readStatus = binding.radioButtonRead.isChecked() ? "Read" : "Want to Read";

        BookEntry bookEntry = new BookEntry(bookTitle, author, readStatus);
        MainActivity mainActivity = (MainActivity) getActivity();

        if (isEditMode) {
            // If it's edit mode, update the existing entry
            mainActivity.updateBookEntry(editPosition, bookEntry);
        } else {
            // If it's add mode, add a new entry
            mainActivity.addBookEntry(bookEntry);
        }

        dismiss();
    }
}
