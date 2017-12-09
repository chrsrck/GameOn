package com.chrsrck.gameon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestActivity extends AppCompatActivity {

    private DatabaseReference messageDatabase;
    private EditText requesterText;
    private Button requestButton;
    private static final String SENDER_ID = "94891811642"; // DO NOT CHANGE
    private AtomicInteger msgId;

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
//             add value event listeners
            newPost.child("content").setValue(requester);

        }


    }
}
