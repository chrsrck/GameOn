package com.chrsrck.gameon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;

public class CheckActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton checkin;
    RadioButton checkout;
    EditText requester;
    EditText location;
    EditText itemId;
    EditText quantity;
    Button barcode;
    Button complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        checkin = findViewById(R.id.checkIn);
        checkout = findViewById(R.id.checkOut);
        requester = findViewById(R.id.inputReporterTextField);
        location = findViewById(R.id.inputLocation);
        itemId = findViewById(R.id.inputSerialTextField);
        quantity = findViewById(R.id.inputQuantity);
        barcode = findViewById(R.id.scanCode);
        complete = findViewById(R.id.checkInOutButton);
        checkin.setOnClickListener(this);
        checkout.setOnClickListener(this);
        barcode.setOnClickListener(this);
        complete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == barcode.getId()) {
            //open camera, etc, may need to save data.
        }
        else if (view.getId() == complete.getId()) {
            String name = requester.getText().toString().trim();
            String loc = location.getText().toString().trim();
            String item = itemId.getText().toString().trim();
            //int quant = Integer.parseInt(quantity.getText().toString().trim());
            //check which radio button is clicked
            //Update server
            Toast.makeText(this, "Successful Submission!",
                    Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something here
                    goHome();
                }
            }, 2000);
        }
    }
    private void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
