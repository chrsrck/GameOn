package com.chrsrck.gameon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;

import com.squareup.picasso.Picasso;

public class DamageActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView pic;
    Button takePic;
    EditText reporter;
    EditText itemId;
    Button barCode;
    EditText desc;
    Button submit;

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
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == takePic.getId()) {
            //open camera
            //update image
        }
        else if (view.getId() == barCode.getId()) {
            //open camera
            //scan barcode
        }
        else if (view.getId() == submit.getId()) {
            //update database
            String name = reporter.getText().toString().trim();
            int id = Integer.parseInt(itemId.getText().toString().trim());
            String description = desc.getText().toString().trim();
        }
    }
}
