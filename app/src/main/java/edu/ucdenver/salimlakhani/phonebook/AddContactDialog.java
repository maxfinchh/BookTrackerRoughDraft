package edu.ucdenver.salimlakhani.phonebook;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import edu.ucdenver.salimlakhani.phonebook.databinding.DialogAddContactBinding;

public class AddContactDialog extends DialogFragment {
    private DialogAddContactBinding binding;

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {

        binding = DialogAddContactBinding.inflate(LayoutInflater.from (getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

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
                    public void onClick (View v) {
                        saveData();
                    }
                }
        );



        return builder.create();
    }

    private void clearForm () {
        binding.textInputName.setText("");
        binding.textInputPhone.setText("");
        binding.textInputEmail.setText("");
        binding.textInputStreet.setText("");
        binding.textInputCity.setText("");
        binding.textInputState.setText("");
        binding.textInputZip.setText("");
        binding.radioButtonBusiness.setChecked(true);
        binding.textInputName.requestFocus();
    }

    private void saveData () {
        String name = binding.textInputName.getText().toString();
        String phone = binding.textInputPhone.getText().toString();
        String email = binding.textInputEmail.getText().toString();
        String street = binding.textInputStreet.getText().toString();
        String city = binding.textInputCity.getText().toString();
        String state = binding.textInputState.getText().toString();
        String zip = binding.textInputZip.getText().toString();

        String contactType = "";

        if (binding.radioButtonBusiness.isChecked()) {
            contactType = "Business";
        } else if (binding.radioButtonFamily.isChecked()) {
            contactType = "Family";
        } else {
            contactType = "Friend";
        }

        Contact contact = new Contact(name, phone, email, street, city, state, zip, contactType);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.addContact(contact);
        dismiss();


    }





}
