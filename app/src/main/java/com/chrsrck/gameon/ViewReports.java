package com.chrsrck.gameon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*
 *  This activity is responsible for setting up the functionality of the view reports screen
 */
public class ViewReports extends AppCompatActivity implements View.OnClickListener {
    Button checked;
    Button damage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        checked = findViewById(R.id.Checked);
        damage = findViewById(R.id.Damage);
        checked.setOnClickListener(this);
        damage.setOnClickListener(this);

        MainActivity.gameOnDatabase.mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
     *  Handles all onClick events for the view reports screen by starting the appropriate activity
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == checked.getId()) {
            Intent intent = new Intent(this, ViewCheckedOutActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, ViewDamagedActivity.class);
            startActivity(intent);
        }
    }
}
