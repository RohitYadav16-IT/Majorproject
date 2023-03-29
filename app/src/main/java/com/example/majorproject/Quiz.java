package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {


    EditText mainUsername;
    private Button mReturnButton, mbeginnerButton;
    String username;

    TextView logoutRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve the username passed from LoginActivity
        username = getIntent().getStringExtra("USERNAME");
        mainUsername = findViewById(R.id.main_username4);

        mbeginnerButton = findViewById(R.id.beginner);
        mReturnButton = findViewById(R.id.return_button);
        logoutRedirectText = findViewById(R.id.logout4);

        mainUsername.setText(username);
        mainUsername.setEnabled(false); // disable editing of the username field

        mbeginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Quiz.this, Beginner.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Quiz.this, Learning.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Quiz.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
