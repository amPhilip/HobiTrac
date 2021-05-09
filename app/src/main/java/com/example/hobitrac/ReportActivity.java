package com.example.hobitrac;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {
    HobbyDao hobbyDao;
    TextView totalHobbies;
    TextView totalHours;
    TextView quote1;
    TextView quote2;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getSupportActionBar().setTitle("Your Hobbies Report");
        getReportData();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getReportData() {

        new Thread(new Runnable() {
            int total = 0;
            int totalNumHobbies = 0;
            @Override
            public void run() {
                HobbyDatabase.getInstance(ReportActivity.this).HobbyDao().getAllHobbies().forEach(hobby -> {
                    // totalHours
                    total = (total + hobby.hobby_time);
                    totalHours = findViewById(R.id.lblTotalHours);
                    totalHours.setText(String.valueOf(total) + "Hrs");

                    // report Info
                    quote1 = findViewById(R.id.quoteOne);

                    if(total <= 5) {
                        quote1.setText("\"Believe in yourself and all that you are. Know that there is something inside of you that is greater than any obstacle.\"");
                    } else if(total > 5 && total <= 10) {
                        quote1.setText("\"Success isn’t always about greatness. It’s about consistency. Consistent hard work gains success. Greatness will come.\"");
                    } else if(total >10 && total <=20) {
                        quote1.setText("\"Your body can stand almost anything, It’s your mind that you have to convince.\"");
                    } else {
                        quote1.setText("\"As long as you keep going, you will never fail. Refuse any other outcome but the one you are aiming for.\"");
                    }
                });

                // totalHobbies
                totalHobbies = findViewById(R.id.lblNumHobbies);
                totalNumHobbies = HobbyDatabase.getInstance(ReportActivity.this).HobbyDao().getAllHobbies().size();
                totalHobbies.setText(String.valueOf(totalNumHobbies));

            }
        }).start();
    }
}