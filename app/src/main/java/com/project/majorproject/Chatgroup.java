package com.project.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.majorproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chatgroup extends AppCompatActivity {

    EditText  enterText;
    Button return1Button, sendButton;
    TextView logoutRedirectText, sendersText, mainUsername;

    String username, language;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgroup);


        username = getIntent().getStringExtra("USERNAME");
        language = getIntent().getStringExtra("LANGUAGE");
        String selectedLanguage1 = getIntent().getStringExtra("LANGUAGE1");
        String selectedLanguage2 = getIntent().getStringExtra("LANGUAGE2");
        String selectedLanguage3 = getIntent().getStringExtra("LANGUAGE3");
        String score = getIntent().getStringExtra("SCORE");

        enterText = findViewById(R.id.enter_chat);
        sendersText = findViewById(R.id.sender_chat);
        mainUsername = findViewById(R.id.main_username3);
        sendButton = findViewById(R.id.send_button);
        return1Button = findViewById(R.id.return1_button);
        logoutRedirectText = findViewById(R.id.logout5);

        mainUsername.setText(username + "--" + language);
//        mainUsername.setEnabled(false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("chat_" + language);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                sendersText.setText("");

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String message = childSnapshot.getValue(String.class);
                    String sender = childSnapshot.getKey();


                    if (!sender.equals(username)) {
                        sendersText.append(sender + ": " + message + "\n");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = enterText.getText().toString();
                reference.child(username).setValue(message);

                enterText.setText("");
            }
        });

        return1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chatgroup.this, Learning.class);
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
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Chatgroup.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mainUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Chatgroup.this, Profile.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }
        });
    }
}
