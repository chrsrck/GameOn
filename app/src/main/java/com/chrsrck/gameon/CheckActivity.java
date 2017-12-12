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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CheckActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton checkin;
    RadioButton checkout;
    EditText requester;
    EditText location;
    EditText itemId;
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
        barcode = findViewById(R.id.scanCode);
        complete = findViewById(R.id.checkInOutButton);
        checkin.setOnClickListener(this);
        checkout.setOnClickListener(this);
        barcode.setOnClickListener(this);
        complete.setOnClickListener(this);
        MainActivity.gameOnDatabase.mContext = this;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == barcode.getId()) {
            //open camera, etc, may need to save data.
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        else if (view.getId() == complete.getId()) {
            String name = requester.getText().toString().trim();
            String loc = location.getText().toString().trim();
            String item = itemId.getText().toString().trim();
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

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            ((EditText)findViewById(R.id.inputSerialTextField)).setText(scanContent);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
