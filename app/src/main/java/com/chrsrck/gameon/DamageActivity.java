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

import com.squareup.picasso.Picasso;

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

    @Override
    public void onClick(View view) {
        if (view.getId() == takePic.getId()) {
            //open camera
            //update image
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        }
        else if (view.getId() == barCode.getId()) {
            //open camera
            //scan barcode
        }
        else if (view.getId() == submit.getId()) {
            //update database
            String name = reporter.getText().toString().trim();
            String idVal = itemId.getText().toString().trim();
            String description = desc.getText().toString().trim();

            int result = MainActivity.gameOnDatabase.addDamageReport(name, idVal, description);
            if(result == -1) {
                Toast.makeText(this, "Damage Report Already Exists", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Damage Report Submitted", Toast.LENGTH_SHORT).show();
            }

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something here
                    goHome();
                }
            }, 2000);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            pic.setImageBitmap(image);
        }
    }

    private void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
