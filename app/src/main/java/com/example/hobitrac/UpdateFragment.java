package com.example.hobitrac;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFragment extends Fragment {
    EditText txtUpdateHobbyName;
    EditText txtUpdateHours;
    EditText txthobbyId;
    private Button btnUpdate;

    HobbyDao hobbyDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update, container, false);
        txthobbyId = view.findViewById(R.id.hobbyID);
        txtUpdateHobbyName = view.findViewById(R.id.txtUpdateHobbyName);
        txtUpdateHours = view.findViewById(R.id.txtUpdateHours);
        btnUpdate = view.findViewById(R.id.updateHobby);

        // AppBar
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("HobiTrac");


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUpdateHobbyName.getText().length() < 3 || txthobbyId.getText().length() <= 0 || txtUpdateHours.getText().length() < 0) {
                    Toast.makeText(getActivity(), "Please enter valid required fields", Toast.LENGTH_LONG).show();
                } else {
                    final Hobby hobby = new Hobby();
                    hobby.hobby_name = txtUpdateHobbyName.getText().toString().trim();
                    hobby.hobby_time = Integer.parseInt(txtUpdateHours.getText().toString().trim());
                    hobby.hobbyId = Integer.parseInt(txthobbyId.getText().toString().trim());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            HobbyDatabase.getInstance(getContext()).HobbyDao().UpdateHobby(hobby);
                        }
                    }).start();

                    Toast.makeText(getActivity(), "Hobby has been updated", Toast.LENGTH_LONG).show();
                    txtUpdateHobbyName.setText("");
                    txtUpdateHours.setText("");
                    txthobbyId.setText("");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }
        });

        return view;
    }
}