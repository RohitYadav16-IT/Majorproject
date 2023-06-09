package com.project.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.majorproject.R;

public class Learning extends AppCompatActivity {

    Button learningButton1, learningButton2, learningButton3, return3Button;



    String username, language, language_one, language_two, language_three, selectedLanguage1, selectedLanguage2, selectedLanguage3;

    TextView logoutRedirectText, language_view, mainUsername;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");
        String selectedLanguage1 = getIntent().getStringExtra("LANGUAGE1");
        String selectedLanguage2 = getIntent().getStringExtra("LANGUAGE2");
        String selectedLanguage3 = getIntent().getStringExtra("LANGUAGE3");

        String language_one = getIntent().getStringExtra("LANGUAGE1");
        String language_two = getIntent().getStringExtra("LANGUAGE2");
        String language_three = getIntent().getStringExtra("LANGUAGE3");

        String score = getIntent().getStringExtra("SCORE");

        mainUsername = findViewById(R.id.main_username2);
        learningButton1 = findViewById(R.id.quiz_button);
        learningButton2 = findViewById(R.id.chat_button);
        learningButton3 = findViewById(R.id.profile_button);
        return3Button = findViewById(R.id.return3_button);
        logoutRedirectText = findViewById(R.id.logout3);
        language_view = findViewById(R.id.language_view);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false);

        language_view.setText("" + language_one + "-" + language_two + "-" + language_three + "");
        language_view.setEnabled(false);
        language_view.setVisibility(View.INVISIBLE);


            learningButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedLanguage1 == null || selectedLanguage1.isEmpty() ||
                            selectedLanguage2 == null || selectedLanguage2.isEmpty() ||
                            selectedLanguage3 == null || selectedLanguage3.isEmpty()) {
                        learningButton1.setEnabled(true);
                        Toast.makeText(Learning.this, "Please select three languages to start the quiz ", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(Learning.this, Quiz.class);
                        intent.putExtra("USERNAME", username);
                        intent.putExtra("LANGUAGE", language);
                        intent.putExtra("LANGUAGE1", selectedLanguage1);
                        intent.putExtra("LANGUAGE2", selectedLanguage2);
                        intent.putExtra("LANGUAGE3", selectedLanguage3);
                        intent.putExtra("SCORE", score);
                        startActivity(intent);
                    }
                }
            });



        learningButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Learning.this, Chatgroup.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }
        });

        learningButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Learning.this, Profile.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }
        });

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        return3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Learning.this, MainActivity.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Learning.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}