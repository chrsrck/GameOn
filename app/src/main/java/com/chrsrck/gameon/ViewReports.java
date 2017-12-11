package com.chrsrck.gameon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == checked.getId()) {

        }
        else {

        }
    }
}
