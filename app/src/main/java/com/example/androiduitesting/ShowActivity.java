// ShowActivity.java
package com.example.androiduitesting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    private TextView cityNameTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Initialize views
        cityNameTextView = findViewById(R.id.city_name_text);
        backButton = findViewById(R.id.back_button);

        // Get the city name from the intent
        String cityName = getIntent().getStringExtra("CITY_NAME");

        // Display the city name
        if (cityName != null) {
            cityNameTextView.setText(cityName);
        }

        // Set up back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to previous activity
            }
        });
    }
}