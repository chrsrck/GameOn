package com.chrsrck.gameon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestActivity extends AppCompatActivity {

    private DatabaseReference messageDatabase;
    private EditText requesterText;
    private Button requestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        requesterText = (EditText) findViewById(R.id.r_RequesterTextField);
        requestButton = (Button) findViewById(R.id.requestButton);
        messageDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");
    }


    public void requestButtonClicked(View view) {
        /*
        Check 36 min mark in the tutorial to implement messaging with users

         */
        String requester = requesterText.getText().toString().trim();
        if (!requester.isEmpty()) {
            final DatabaseReference newPost = messageDatabase.push();
            // add value event listeners
            newPost.child("content").setValue(requester);
        }
    }
}
