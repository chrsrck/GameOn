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
import android.widget.Toast;

/*
 *  Requests Activity is responsible for all functionality when a scorekeeper wants to submit a
 *  request
 */
public class RequestActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText requesterText;
    private Spinner itemText;
    private EditText quantityText;
    private EditText locationText;
    private Button requestButton;

    private static final String SENDER_ID = "94891811642"; // DO NOT CHANGE

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

        //Get spinner options from last ChooseSportActivity
        Intent intent = this.getIntent();
        String[] items = intent.getStringArrayExtra(ChooseSportActivity.OPTIONS);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        itemText.setAdapter(adapter);

        MainActivity.gameOnDatabase.mContext = this;
    }

    /*
     *  Adds the home button to the status bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     *  When the home button is clicked from the menu bar it takes you back to the home screen
     */
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

    /*
     *  This method takes all editTexts from the screen and sends the request to the database if
     *  it is a valid request. It also sends out a notification when a request has been submitted.
     */
    public void requestButtonClicked(View view) {
        String requester = requesterText.getText().toString().trim();
        String item = itemText.getSelectedItem().toString().trim();
        String quantity = quantityText.getText().toString().trim();
        String location = locationText.getText().toString().trim();
        if (requester.equals("")) {
            Toast.makeText(this, "Missing field: Requester", Toast.LENGTH_SHORT).show();
        }
        else if (quantity.equals("")) {
            Toast.makeText(this, "Missing field: Quantity", Toast.LENGTH_SHORT).show();
        }
        //making sure a super large quantity does not crash our application
        //leading with 18 zeros will still get captured by this if statement
        else if (quantity.length() > 18) {
            Toast.makeText(this, "Quantity is way too large!", Toast.LENGTH_SHORT).show();
        }
        else if (quantity.equals("0")) {
            Toast.makeText(this, "Quantity cannot be 0!", Toast.LENGTH_SHORT).show();
        }
        else if (location.equals("")) {
            Toast.makeText(this, "Missing field: Location", Toast.LENGTH_SHORT).show();
        }
        else {
            MainActivity.gameOnDatabase.addToRequestDatabase(requester, item, Long.parseLong(quantity), location, "DEC 09 2017");
            MainActivity.gameOnDatabase.triggerNotification();
            Toast.makeText(this, "Successful Request!", Toast.LENGTH_SHORT).show();
            quantityText.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == requestButton.getId()) {
            requestButtonClicked(view);
        }
    }
}
