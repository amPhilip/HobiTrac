package com.example.hobitrac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransactionKt;
import androidx.room.Update;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button updatebtn;
    private Button deletebtn;
    EditText txtHobbyName;
    EditText txtHours;
    HobbyDao hobbyDao;
    ListView listView;
    Handler handler = new Handler();


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHobbyName = findViewById(R.id.txtHobbyName);
        txtHours = findViewById(R.id.txtHours);
        listView = findViewById(R.id.studentListView);
        HobbyDatabase db = HobbyDatabase.getInstance(this);
        hobbyDao = db.HobbyDao();
        loadHobbiesData();

        // AppBar
        getSupportActionBar().setTitle("Welcome to HobiTrac");


        // Open update fragment
        updatebtn = (Button) findViewById(R.id.update);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainContainer, new UpdateFragment(), "").addToBackStack("").commit();
            }
        });

        deletebtn = (Button) findViewById(R.id.delete);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainContainer, new DeleteFragment(), "").addToBackStack("").commit();
            }
        });

    }



    // show Icon on AppBar
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }



    // Open report on Click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.report:
                startActivity(new Intent(MainActivity.this, ReportActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // CREATE, LOAD HOBBIES
    // Add Hobby Section
    private void addHobby() {
        final Hobby hobby = new Hobby();
        hobby.hobby_name = txtHobbyName.getText().toString().trim();
        hobby.hobby_time = Integer.parseInt(txtHours.getText().toString().trim());

        new Thread(new Runnable() {
            @Override
            public void run() {
                hobbyDao.addHobby(hobby);
            }
        }).start();
        Toast.makeText(this, "Hobby Added Successfully", Toast.LENGTH_LONG).show();
        txtHobbyName.setText("");
        txtHours.setText("");
    }


    private void loadHobbiesData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Cursor cursor = hobbyDao.getAllHobbiesCursor();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,
                                R.layout.hobby_item,
                                cursor,
                                new String[] {"_id", "hobby_name", "hobby_time" },
                                new int [] { R.id.lblHobbyID, R.id.lblHobbyName, R.id.lblHours });

                        listView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }


    public void addHobbyOnSubmit(View view) {
        if(txtHobbyName.getText().length() < 3 || txtHours.getText().length() <= 0) {
            Toast.makeText(this, "Please provide valid information required", Toast.LENGTH_LONG).show();
        } else {
            addHobby();
            loadHobbiesData();
        }
    }
}