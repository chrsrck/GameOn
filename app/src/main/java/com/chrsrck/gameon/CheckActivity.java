package com.chrsrck.gameon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;

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

            if(checkin.isChecked()) {
                if(name.equals("") || loc.equals("") || item.equals("")) {
                    Toast.makeText(this, "Missing Fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int result = MainActivity.gameOnDatabase.returnEquipment(item);
                    if(result == -1) {
                        Toast.makeText(this, "Invalid Item ID: Must be SI-1 through SI-1128", Toast.LENGTH_LONG).show();
                    }
                    else if(result == -2) {
                        Toast.makeText(this, "Item already returned", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "Successfully returned " + name, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else if(checkout.isChecked()) {
                if(name.equals("") || loc.equals("") || item.equals("")) {
                    Toast.makeText(this, "Missing Fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int result = MainActivity.gameOnDatabase.borrowEquipment(name, loc, item);
                    if(result == -1) {
                        Toast.makeText(this, "Invalid Item ID: Must be SI-1 through SI-1128", Toast.LENGTH_LONG).show();
                    }
                    else if(result == -2) {
                        Toast.makeText(this, "Item unavailable: Broken", Toast.LENGTH_SHORT).show();
                    }
                    else if(result == -3) {
                        Toast.makeText(this, "Item unavailable: Borrowed", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "Successfully Borrowed " + name, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(this, "Select a Check option!", Toast.LENGTH_SHORT).show();
            }
            itemId.setText("");
        }
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
