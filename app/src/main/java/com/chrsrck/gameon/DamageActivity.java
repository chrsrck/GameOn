package com.chrsrck.gameon;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

/*
 *  This Activity handles all functionality of the damage report screen
 */
public class DamageActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView pic;
    Button takePic;
    EditText reporter;
    EditText itemId;
    Button barCode;
    EditText desc;
    Button submit;
    private static final int CAMERA_PIC_REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage);

        pic = findViewById(R.id.pictureFiles);
        takePic = findViewById(R.id.takePictureButton);
        reporter = findViewById(R.id.inputReporterTextField);
        itemId = findViewById(R.id.inputSerialTextField);
        barCode = findViewById(R.id.scanCode);
        desc = findViewById(R.id.descriptionTextField);
        submit = findViewById(R.id.submitReport);

        takePic.setOnClickListener(this);
        barCode.setOnClickListener(this);
        submit.setOnClickListener(this);

        Picasso.with(this).load("http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/device-camera-icon.png").into(pic);
        pic.setVisibility(View.VISIBLE);
        MainActivity.gameOnDatabase.mContext = this;
    }

    /*
     *  Handles all onClick events for this screen
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == takePic.getId()) {
            //Opens the camera for the user to take a picture of the damaged item
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        }
        else if (view.getId() == barCode.getId()) {
            //Opens the barcode reader to scan for the item barcode
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        else if (view.getId() == submit.getId()) {
            //Grab all editText fields
            String name = reporter.getText().toString().trim();
            String idVal = itemId.getText().toString().trim();
            String description = desc.getText().toString().trim();

            //Checks if any of the fields are empty
            if (name.equals("") || idVal.equals("") || description.equals("")) {
                Toast.makeText(this, "Missing Fields!", Toast.LENGTH_SHORT).show();
            }
            else {
                int result = MainActivity.gameOnDatabase.addDamageReport(name, idVal, description);
                if(result == -1) {
                    Toast.makeText(this, "Invalid Item ID: Must be SI-1 through SI-1128", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this, "Damage Report Submitted", Toast.LENGTH_SHORT).show();
                    //Delays moving back to home screen by 2 seconds
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something here
                            goHome();
                        }
                    }, 2000);
                }
            }
        }
    }

    /*
     *  Gets the result from both the barcode reader and camera activity
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Checks for the camera activity result
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            pic.setImageBitmap(image);
        }
        //If it got the result and it wasnt for the take picture activity then it was for the barcode activity
        else {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
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

    /*
     *  Take the user back to the home activity
     */
    private void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
