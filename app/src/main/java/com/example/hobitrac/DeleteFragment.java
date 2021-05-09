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

public class DeleteFragment extends Fragment {
    private Button deleteBtn;
    EditText txthobbyId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete, container, false);
        txthobbyId = view.findViewById(R.id.txtDeleteID);
        deleteBtn = view.findViewById(R.id.deleteHobby);

        // AppBar
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("HobiTrac");



        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txthobbyId.getText().length() <= 0) {
                    Toast.makeText(getActivity(), "Please enter valid ID", Toast.LENGTH_LONG).show();
                } else {
                    final Hobby hobby = new Hobby();
                    hobby.hobbyId = Integer.parseInt(txthobbyId.getText().toString().trim());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            HobbyDatabase.getInstance(getContext()).HobbyDao().deleteHobby(hobby);
                        }
                    }).start();

                    Toast.makeText(getActivity(), "Hobby has been deleted", Toast.LENGTH_LONG).show();
                    txthobbyId.setText("");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }
        });

        return view;
    }
}