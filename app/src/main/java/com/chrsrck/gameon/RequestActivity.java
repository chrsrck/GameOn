package com.chrsrck.gameon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.concurrent.atomic.AtomicInteger;

public class RequestActivity extends AppCompatActivity implements View.OnClickListener {

    private GameOnDatabase mGameOnDatabase;

    private EditText requesterText;
    private Spinner itemText;
    private EditText quantityText;
    private EditText locationText;
    private Button requestButton;

    private static final String SENDER_ID = "94891811642"; // DO NOT CHANGE
    private AtomicInteger msgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        requesterText = findViewById(R.id.r_RequesterTextField);
        itemText = findViewById(R.id.r_ItemTextField);
        quantityText = findViewById(R.id.r_QuantityTextField);
        locationText = findViewById(R.id.r_LocationTextField);
        requestButton = findViewById(R.id.requestButton);
        requestButton.setOnClickListener(this);
        Intent intent = this.getIntent();
        String[] items = intent.getStringArrayExtra(ChooseSportActivity.OPTIONS);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        itemText.setAdapter(adapter);
        MainActivity.gameOnDatabase.mContext = this;
//        requestButton = (Button) findViewById(R.id.requestButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home_button) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void requestButtonClicked(View view) {
        /*
        Check 36 min mark in the tutorial to implement messaging with users

         */
        String requester = requesterText.getText().toString().trim();
        String item = itemText.toString().trim();
        String quantity = quantityText.getText().toString().trim();
        String location = locationText.getText().toString().trim();
        String request = "";
        if (!requester.isEmpty()) {
//            final DatabaseReference newPost = messageDatabase.push();
////             add value event listeners
//            newPost.child("content").setValue(requester);
//            mGameOnDatabase.addToMessageDatabase(requester);

             MainActivity.gameOnDatabase.addToRequestDatabase(requester, item, Long.parseLong(quantity), location, "DEC 09 2017");
             MainActivity.gameOnDatabase.triggerNotification();
        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == requestButton.getId()) {
            MainActivity.RequestMade = 1;
            requestButtonClicked(view);
        }
    }
}
