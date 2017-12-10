package com.chrsrck.gameon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    Button check;
    Button request;
    Button damage;
    Button options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String email = intent.getStringExtra(MainActivity.USERNAME);
        String password = intent.getStringExtra(MainActivity.PASSWORD);
        check = findViewById(R.id.Check);
        request = findViewById(R.id.Request);
        damage = findViewById(R.id.Damage);
        options = findViewById(R.id.Options);
        check.setOnClickListener(this);
        request.setOnClickListener(this);
        damage.setOnClickListener(this);
        options.setOnClickListener(this);

        if (email.equals("1")) {
            check.setVisibility(View.GONE);
            damage.setVisibility(View.GONE);
        }
        else if (email.equals("2")) {
            request.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == check.getId()) {
            intent = new Intent(this, CheckActivity.class);
        }
        else if (view.getId() == request.getId()) {
            intent = new Intent(this, RequestActivity.class);
        }
        else if (view.getId() == damage.getId()) {
            intent = new Intent(this, DamageActivity.class);
        }
        else {
            intent = new Intent(this, OptionsActivity.class);
        }
        startActivity(intent);
    }
}
