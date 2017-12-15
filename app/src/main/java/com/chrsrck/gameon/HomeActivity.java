package com.chrsrck.gameon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 *  This class handles all functionality of the home screen (after the login screen)
 *  It is tailored to both scorekeepers and supervisors based on login information
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    Button check;
    Button request;
    Button damage;
    Button viewrequests;
    Button viewreports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        check = findViewById(R.id.Check);
        request = findViewById(R.id.Request);
        damage = findViewById(R.id.Damage);
        viewrequests = findViewById(R.id.ViewRequest);
        viewreports = findViewById(R.id.ViewReport);

        check.setOnClickListener(this);
        request.setOnClickListener(this);
        damage.setOnClickListener(this);
        viewreports.setOnClickListener(this);
        viewrequests.setOnClickListener(this);

        //Sets the visibility of the buttons per user type
        if (MainActivity.username.equals("scorekeeper@vt.edu")) {
            check.setVisibility(View.GONE);
            damage.setVisibility(View.GONE);
            viewreports.setVisibility(View.GONE);
        }
        else if (MainActivity.username.equals("supervisor@vt.edu")) {
            request.setVisibility(View.GONE);
        }
        MainActivity.gameOnDatabase.mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
     * Handles all onClick events for this activity by starting the appropriate activities
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == check.getId()) {
            intent = new Intent(this, CheckActivity.class);
        }
        else if (view.getId() == request.getId()) {
            intent = new Intent(this, ChooseSportActivity.class);
        }
        else if (view.getId() == damage.getId()) {
            intent = new Intent(this, DamageActivity.class);
        }
        else if (view.getId() == viewrequests.getId()) {
            intent = new Intent(this, ViewRequests.class);
        }
        else {
            intent = new Intent(this, ViewReports.class);
        }
        startActivity(intent);
    }
}
